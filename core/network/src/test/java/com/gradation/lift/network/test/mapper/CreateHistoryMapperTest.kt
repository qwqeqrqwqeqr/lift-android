package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.History.CREATE_HISTORY_MODEL
import com.gradation.lift.network.data.TestDtoDataGenerator.History.CreateHistory.CREATE_HISTORY_REQUEST_DTO
import com.gradation.lift.network.mapper.toDto
import kotlinx.coroutines.test.runTest
import org.junit.Test


class CreateHistoryMapperTest {
    @Test
    fun createHistoryMapper() = runTest {

        with(CREATE_HISTORY_MODEL.toDto()) {
            Truth.assertThat(score).isEqualTo(CREATE_HISTORY_REQUEST_DTO.score)
            Truth.assertThat(comment).isEqualTo(CREATE_HISTORY_REQUEST_DTO.comment)
            Truth.assertThat(workTime).isEqualTo(CREATE_HISTORY_REQUEST_DTO.workTime)
            Truth.assertThat(restTime).isEqualTo(CREATE_HISTORY_REQUEST_DTO.restTime)
            Truth.assertThat(totalTime).isEqualTo(CREATE_HISTORY_REQUEST_DTO.totalTime)
            Truth.assertThat(historyTimeStamp)
                .isEqualTo(CREATE_HISTORY_REQUEST_DTO.historyTimeStamp)
            Truth.assertThat(historyRoutine).isEqualTo(CREATE_HISTORY_REQUEST_DTO.historyRoutine)
        }
    }
}