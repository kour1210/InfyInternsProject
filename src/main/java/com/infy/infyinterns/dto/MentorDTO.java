package com.infy.infyinterns.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MentorDTO {
    @NotNull(message = "{mentor.id.notnull}")
    @Size(min = 4, max = 4, message = "{mentor.id.invalid}")
    private Integer mentorId;
    private String mentorName;
    private Integer numberOfProjectsMentored;

    // Getters and Setters
    public Integer getMentorId() {
        return mentorId;
    }

    public void setMentorId(Integer mentorId) {
        this.mentorId = mentorId;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public Integer getNumberOfProjectsMentored() {
        return numberOfProjectsMentored;
    }

    public void setNumberOfProjectsMentored(Integer numberOfProjectsMentored) {
        this.numberOfProjectsMentored = numberOfProjectsMentored;
    }
}


