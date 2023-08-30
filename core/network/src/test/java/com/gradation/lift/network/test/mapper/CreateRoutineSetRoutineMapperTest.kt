package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.createRoutineSetRoutineModel
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSet.createRoutineSetRoutineRequestDto
import com.gradation.lift.network.mapper.toDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class CreateRoutineSetRoutineMapperTest {

    @Test
    fun createRoutineSetRoutineMapper() = runTest {

        with(createRoutineSetRoutineModel.toDto()) {
            Truth.assertThat(this.name).isEqualTo(createRoutineSetRoutineRequestDto.name)
            Truth.assertThat(this.description).isEqualTo(createRoutineSetRoutineRequestDto.description)
            Truth.assertThat(this.weekday).isEqualTo(createRoutineSetRoutineRequestDto.weekday)
            Truth.assertThat(this.picture).isEqualTo(createRoutineSetRoutineRequestDto.picture)
            Truth.assertThat(this.routine)
                .isEqualTo(createRoutineSetRoutineRequestDto.routine)
        }
    }
}