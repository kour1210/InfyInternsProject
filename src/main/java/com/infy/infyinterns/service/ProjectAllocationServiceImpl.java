package com.infy.infyinterns.service;

import com.infy.infyinterns.dto.MentorDTO;

import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.entity.Mentor;
import com.infy.infyinterns.entity.Project;
import com.infy.infyinterns.exception.InfyInternException;
import com.infy.infyinterns.repository.MentorRepository;
import com.infy.infyinterns.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("projectService")
@Transactional
public class ProjectAllocationServiceImpl implements ProjectAllocationService {
    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // Allocate a project to a mentor
    @Override
    public Integer allocateProject(ProjectDTO projectDTO) throws InfyInternException {
        Optional<Mentor> optionalMentor = mentorRepository.findById(projectDTO.getMentorDTO().getMentorId());
        if (optionalMentor.isEmpty()) {
            throw new InfyInternException("Service.MENTOR_NOT_FOUND");
        }

        Mentor mentor = optionalMentor.get();

        if (mentor.getNumberOfProjectsMentored() >= 3) {
            throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");
        }

        Project project = new Project();
        project.setProjectName(projectDTO.getProjectName());
        project.setIdeaOwner(projectDTO.getIdeaOwner());
        project.setReleaseDate(projectDTO.getReleaseDate());
        project.setMentor(mentor);

        mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored() + 1);

        projectRepository.save(project);
        return project.getProjectId();
    }

    // Retrieve mentors based on the number of projects they are mentoring
    @Override
    public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InfyInternException{
        List<Mentor> mentors = mentorRepository.findByNumberOfProjectsMentored(numberOfProjectsMentored);

        if (mentors.isEmpty()) {
            throw new InfyInternException("Service.MENTOR_NOT_FOUND");
        }

        List<MentorDTO> mentorDTOList = new ArrayList<>();
        for (Mentor mentor : mentors) {
            MentorDTO mentorDTO = new MentorDTO();
            mentorDTO.setMentorId(mentor.getMentorId());
            mentorDTO.setMentorName(mentor.getMentorName());
            mentorDTO.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored());
            mentorDTOList.add(mentorDTO);
        }

        return mentorDTOList;
    }

    // Update the mentor of a project
    @Override
    public void updateProjectMentor(Integer projectId, Integer mentorId)  throws InfyInternException{
        Optional<Mentor> optionalMentor = mentorRepository.findById(mentorId);
        if (optionalMentor.isEmpty()) {
            throw new InfyInternException("Service.MENTOR_NOT_FOUND");
        }

        Mentor mentor = optionalMentor.get();
        if (mentor.getNumberOfProjectsMentored() >= 3) {
            throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");
        }

        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            throw new InfyInternException("Service.PROJECT_NOT_FOUND");
        }

        Project project = optionalProject.get();

        Mentor previousMentor = project.getMentor();
        if (previousMentor != null) {
            previousMentor.setNumberOfProjectsMentored(previousMentor.getNumberOfProjectsMentored() - 1);
        }

        project.setMentor(mentor);
        mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored() + 1);

        projectRepository.save(project);
    }

    // Delete a project
    @Override
    public void deleteProject(Integer projectId) throws InfyInternException {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            throw new InfyInternException("Service.PROJECT_NOT_FOUND");
        }

        Project project = optionalProject.get();
        Mentor mentor = project.getMentor();

        if (mentor != null) {
            mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored() - 1);
            project.setMentor(null);
        }

        projectRepository.delete(project);
    }
}
