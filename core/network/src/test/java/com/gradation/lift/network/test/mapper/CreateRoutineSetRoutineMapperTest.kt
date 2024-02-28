package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.CREATE_ROUTINE_SET_ROUTINE_MODEL
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.CreateRoutineSetRoutine.CREATE_ROUTINE_SET_ROUTINE_REQUEST_DTO
import com.gradation.lift.network.mapper.toDto
import kotlinx.coroutines.test.runTest
import org.junit.Test


class CreateRoutineSetRoutineMapperTest {

    @Test
    fun createRoutineSetRoutineMapper() = runTest {
        with(CREATE_ROUTINE_SET_ROUTINE_MODEL.toDto()) {
            Truth.assertThat(name).isEqualTo(CREATE_ROUTINE_SET_ROUTINE_REQUEST_DTO.name)
            Truth.assertThat(description)
                .isEqualTo(CREATE_ROUTINE_SET_ROUTINE_REQUEST_DTO.description)
            Truth.assertThat(weekday).isEqualTo(CREATE_ROUTINE_SET_ROUTINE_REQUEST_DTO.weekday)
            Truth.assertThat(label).isEqualTo(CREATE_ROUTINE_SET_ROUTINE_REQUEST_DTO.label)
            Truth.assertThat(picture).isEqualTo(CREATE_ROUTINE_SET_ROUTINE_REQUEST_DTO.picture)
            Truth.assertThat(routine).isEqualTo(CREATE_ROUTINE_SET_ROUTINE_REQUEST_DTO.routine)
        }
    }
}