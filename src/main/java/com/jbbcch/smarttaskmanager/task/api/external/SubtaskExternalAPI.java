package com.jbbcch.smarttaskmanager.task.api.external;

import com.jbbcch.smarttaskmanager.task.dto.SubtaskResponse;

import java.util.List;

// technically still only used inside the task module but by a different controller,
// so still separate from the internal api.
public interface SubtaskExternalAPI {
    List<SubtaskResponse> getSubtasksByTaskId(Long taskId);
}
