package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.updateRoutineSetCountModel
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.updateRoutineSetRoutineModel
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSet.updateRoutineSetRoutineRequestDto
import com.gradation.lift.network.mapper.toDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class UpdateRoutineSetCountMapperTest {

    @Test
    fun updateRoutineSetCountMapper() = runTest {
        with(updateRoutineSetCountModel.toDto()) {
            Truth.assertThat(this.id).isEqualTo(updateRoutineSetRoutineRequestDto.id)
        }
    }
}