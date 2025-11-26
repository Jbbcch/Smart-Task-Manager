package com.jbbcch.smarttaskmanager.project.mapper;

import com.jbbcch.smarttaskmanager.project.dto.ProjectRequest;
import com.jbbcch.smarttaskmanager.project.dto.ProjectResponse;
import com.jbbcch.smarttaskmanager.project.model.entity.Project;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {
    ProjectResponse projectToProjectResponse(Project project);
    Project projectRequestToProject(ProjectRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProjectFromRequest(ProjectRequest request, @MappingTarget Project project);
}
