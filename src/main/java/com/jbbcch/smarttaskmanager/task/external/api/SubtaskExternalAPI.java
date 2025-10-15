package com.jbbcch.smarttaskmanager.task.external.api;

import com.jbbcch.smarttaskmanager.task.external.dto.SubtaskRequest;
import com.jbbcch.smarttaskmanager.task.external.dto.SubtaskResponse;

public interface SubtaskExternalAPI {
    SubtaskResponse createSubtaskForTaskId(Long taskId, SubtaskRequest request);
    SubtaskResponse updateSubtaskById(Long id, SubtaskRequest request);
    SubtaskResponse deleteSubtaskById(Long id);
    // Tasks should be automatically fetched with every task, so no method for that here
}
