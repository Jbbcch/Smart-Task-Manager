package com.jbbcch.smarttaskmanager.project.mapper;

import com.jbbcch.smarttaskmanager.project.dto.ProjectRequest;
import com.jbbcch.smarttaskmanager.project.dto.ProjectResponse;
import com.jbbcch.smarttaskmanager.project.model.entity.Project;
import org.mapstruct.Mapper;

@Mapper
public interface ProjectMapper {
    ProjectResponse projectToProjectResponse(Project project);
    Project projectRequestToProject(ProjectRequest request);
}
