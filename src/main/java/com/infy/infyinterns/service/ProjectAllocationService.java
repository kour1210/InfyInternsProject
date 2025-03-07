package com.infy.infyinterns.service;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.exception.InfyInternException;

import java.util.List;

public interface ProjectAllocationService {
    Integer allocateProject(ProjectDTO project) throws InfyInternException;
    List<MentorDTO> getMentors(Integer numberOfProjectsMentored)throws InfyInternException;
    void updateProjectMentor(Integer projectId, Integer mentorId) throws InfyInternException;
    void deleteProject(Integer projectId) throws InfyInternException;
}
