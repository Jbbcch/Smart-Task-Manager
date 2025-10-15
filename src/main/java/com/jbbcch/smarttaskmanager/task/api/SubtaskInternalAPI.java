package com.jbbcch.smarttaskmanager.task.api;

import com.jbbcch.smarttaskmanager.task.dto.SubtaskRequest;
import com.jbbcch.smarttaskmanager.task.dto.SubtaskResponse;

public interface SubtaskInternalAPI {
    SubtaskResponse createSubtask(SubtaskRequest request);
    SubtaskResponse updateSubtaskById(Long id, SubtaskRequest request);
    SubtaskResponse deleteSubtaskById(Long id);
    // Tasks should be automatically fetched with every task, so no method for that here
}
