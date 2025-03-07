package com.infy.infyinterns.api;

import com.infy.infyinterns.dto.MentorDTO;


import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.exception.InfyInternException;
import com.infy.infyinterns.service.ProjectAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/infyinterns")
@Validated
public class ProjectAllocationAPI {
    @Autowired
    private ProjectAllocationService projectAllocationService;

    @Autowired
    private Environment environment;

    // Allocate a project to a mentor
    @PostMapping("/project")
    public ResponseEntity<String> allocateProject(@Valid @RequestBody ProjectDTO projectDTO) throws InfyInternException {
        Integer projectId = projectAllocationService.allocateProject(projectDTO);
        String successMessage = environment.getProperty("API.ALLOCATION_SUCCESS") + ": " + projectId;
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }

    // Get mentors based on the number of projects mentored
    @GetMapping("/mentor/{numberOfProjectsMentored}")
    public ResponseEntity<List<MentorDTO>> getMentors(@PathVariable Integer numberOfProjectsMentored) throws InfyInternException{
        List<MentorDTO> mentors = projectAllocationService.getMentors(numberOfProjectsMentored);
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

    // Update the mentor of a project
    @PutMapping("/project/{projectId}/{mentorId}")
    public ResponseEntity<String> updateProjectMentor(
        @PathVariable Integer projectId,
        @PathVariable @Digits(integer = 4, fraction = 0, message = "{mentor.id.invalid}") Integer mentorId) throws InfyInternException{
        
        projectAllocationService.updateProjectMentor(projectId, mentorId);
        String successMessage = environment.getProperty("API.PROJECT_UPDATE_SUCCESS");
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }

    // Delete a project
    @DeleteMapping("/project/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable Integer projectId) throws InfyInternException {
        projectAllocationService.deleteProject(projectId);
        String successMessage = environment.getProperty("API.PROJECT_DELETE_SUCCESS");
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }
}
