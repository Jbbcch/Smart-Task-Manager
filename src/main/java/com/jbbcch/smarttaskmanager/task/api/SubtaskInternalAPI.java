package com.jbbcch.smarttaskmanager.task.api;

import com.jbbcch.smarttaskmanager.task.dto.SubtaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.SubtaskResponse;

import java.util.List;

public interface SubtaskInternalAPI {
    SubtaskResponse createSubtask(SubtaskRequest request);
    SubtaskResponse updateSubtaskById(Long id, SubtaskRequest request);
    SubtaskResponse deleteSubtaskById(Long id);
    SubtaskResponse switchStatusById(Long subtaskId);
}
