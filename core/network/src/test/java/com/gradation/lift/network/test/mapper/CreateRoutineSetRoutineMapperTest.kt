package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.createRoutineSetRoutineModel
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSet.createRoutineSetRequestDto
import com.gradation.lift.network.mapper.toDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class CreateRoutineSetRoutineMapperTest {

    @Test
    fun testCreateRoutineSetRoutineMapper() = runTest {

        with(createRoutineSetRoutineModel.toDto()) {
            Truth.assertThat(this.name).isEqualTo(createRoutineSetRequestDto.name)
            Truth.assertThat(this.description).isEqualTo(createRoutineSetRequestDto.description)
            Truth.assertThat(this.weekday).isEqualTo(createRoutineSetRequestDto.weekday)
            Truth.assertThat(this.picture).isEqualTo(createRoutineSetRequestDto.picture)
            Truth.assertThat(this.routine)
                .isEqualTo(createRoutineSetRequestDto.routine)
        }
    }
}