package com.infy.infyinterns.dto;

import jakarta.validation.constraints.NotNull;

public class ProjectDTO {
    private Integer projectId;

    @NotNull(message = "{project.name.notnull}")
    private String projectName;

    @NotNull(message = "{project.ideaOwner.notnull}")
    private String ideaOwner;

    @NotNull(message = "{project.releaseDate.notnull}")
    private String releaseDate;

    private MentorDTO mentorDTO;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getIdeaOwner() {
		return ideaOwner;
	}

	public void setIdeaOwner(String ideaOwner) {
		this.ideaOwner = ideaOwner;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public MentorDTO getMentorDTO() {
		return mentorDTO;
	}

	public void setMentorDTO(MentorDTO mentorDTO) {
		this.mentorDTO = mentorDTO;
	}

    
}
