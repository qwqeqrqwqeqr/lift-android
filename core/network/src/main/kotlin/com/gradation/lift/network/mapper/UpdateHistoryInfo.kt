package com.gradation.lift.network.mapper

import com.gradation.lift.model.model.history.UpdateHistoryInfo
import com.gradation.lift.network.dto.history.UpdateHistoryInfoRequestDto


fun UpdateHistoryInfo.toDto() : UpdateHistoryInfoRequestDto =
    UpdateHistoryInfoRequestDto(historyId, comment, score)
