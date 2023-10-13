package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.updateRoutineSetRoutineModel
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSet.updateRoutineSetRoutineRequestDto
import com.gradation.lift.network.mapper.toDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class UpdateRoutineSetRoutineMapperTest {

    @Test
    fun updateRoutineSetRoutineMapper() = runTest {
        with(updateRoutineSetRoutineModel.toDto()) {
            Truth.assertThat(this.name).isEqualTo(updateRoutineSetRoutineRequestDto.name)
            Truth.assertThat(this.description)
                .isEqualTo(updateRoutineSetRoutineRequestDto.description)
            Truth.assertThat(this.weekday).isEqualTo(updateRoutineSetRoutineRequestDto.weekday)
            Truth.assertThat(this.label).isEqualTo(updateRoutineSetRoutineRequestDto.label)
            Truth.assertThat(this.picture).isEqualTo(updateRoutineSetRoutineRequestDto.picture)
            Truth.assertThat(this.routine)
                .isEqualTo(updateRoutineSetRoutineRequestDto.routine)
        }
    }
}