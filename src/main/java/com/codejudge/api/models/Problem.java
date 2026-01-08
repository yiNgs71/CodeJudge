package com.codejudge.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "problem")
@Getter @Setter
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "Text")
    private String description;

    private String level;

    private String restrictions;
    @Column(columnDefinition = "Text")
    private String expectedoutput;
    @Column(columnDefinition = "Text")
    private String expectedinput;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getExpectedoutput() {
        return expectedoutput;
    }

    public void setExpectedoutput(String expectedoutput) {
        this.expectedoutput = expectedoutput;
    }

    public String getExpectedinput() {
        return expectedinput;
    }

    public void setExpectedinput(String expectedinput) {
        this.expectedinput = expectedinput;
    }
}
