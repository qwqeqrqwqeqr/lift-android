package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.History.createHistoryModel
import com.gradation.lift.network.data.TestDtoDataGenerator.History.createHistoryRequestDto
import com.gradation.lift.network.mapper.toDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class CreateHistoryMapperTest {

    @Test
    fun createHistoryMapper() = runTest {

        with(createHistoryModel.toDto()) {
            Truth.assertThat(this.score).isEqualTo(createHistoryRequestDto.score)
            Truth.assertThat(this.comment).isEqualTo(createHistoryRequestDto.comment)
            Truth.assertThat(this.workTime).isEqualTo(createHistoryRequestDto.workTime)
            Truth.assertThat(this.restTime).isEqualTo(createHistoryRequestDto.restTime)
            Truth.assertThat(this.totalTime).isEqualTo(createHistoryRequestDto.totalTime)
            Truth.assertThat(this.historyTimeStamp).isEqualTo(createHistoryRequestDto.historyTimeStamp)
            Truth.assertThat(this.historyRoutine).isEqualTo(createHistoryRequestDto.historyRoutine)
        }
    }
}