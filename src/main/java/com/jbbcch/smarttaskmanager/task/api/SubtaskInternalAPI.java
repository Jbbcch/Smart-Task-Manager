package com.jbbcch.smarttaskmanager.task.api;

import com.jbbcch.smarttaskmanager.task.dto.SubtaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.SubtaskResponse;

public interface SubtaskInternalAPI {
    SubtaskResponse updateSubtaskById(Long id, SubtaskRequest request);
    SubtaskResponse deleteSubtaskById(Long id);
    SubtaskResponse switchStatusById(Long subtaskId);
}
