package com.codejudge.api.services;

import com.codejudge.api.models.*;
import com.codejudge.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.*;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import com.codejudge.api.models.User;

@Service
public class SubmissionServiceImpl implements ISubmissioService {
    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private UserRepository userRepository;

    public Submission processSubmission(Submission submission) {
        User user = userRepository.findById(submission.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Problem problem = problemRepository.findById(submission.getProblem().getId())
                .orElseThrow(() -> new RuntimeException("Problema no encontrado"));

        submission.setUser(user);
        submission.setProblem(problem);
        submission.setStatus("PENDING");

        submission = submissionRepository.save(submission);
        return runExecutionLogic(submission);
    }

    private Submission runExecutionLogic(Submission submission) {
        String extension = submission.getLanguage().equalsIgnoreCase("python") ? ".py" : ".txt";
        String fileName = "Solution_" + UUID.randomUUID().toString() + extension;
        Path path = Paths.get("temp/" + fileName);

        try {
            Files.createDirectories(path.getParent());
            Files.writeString(path, submission.getCode());

            ProcessBuilder pb = new ProcessBuilder("python3", path.toString());
            Process process = pb.start();

            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
                writer.write(submission.getProblem().getExpectedinput());
                writer.flush();
            }
            boolean finished = process.waitFor(5, TimeUnit.SECONDS);

            if (!finished) {
                process.destroyForcibly();
                submission.setStatus("TIME LIMIT EXCEEDED");
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line);
                }
                System.out.println("Salida ---> "+output.toString().trim());
                if (output.toString().trim().equals(submission.getProblem().getExpectedoutput())) {
                    submission.setStatus("ACCEPTED");
                } else {
                    submission.setStatus("WRONG ANSWER");
                }
            }

        } catch (IOException | InterruptedException e) {
            submission.setStatus("RUNTIME ERROR");
            Thread.currentThread().interrupt();
        } finally {
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                System.err.println("No se pudo eliminar el archivo temporal: " + path);
            }
        }

        return submissionRepository.save(submission);
    }
}