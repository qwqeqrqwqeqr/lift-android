package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.UPDATE_ROUTINE_SET_ROUTINE_MODEL
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.UpdateRoutineSetRoutine.UPDATE_ROUTINE_SET_ROUTINE_REQUEST_DTO
import com.gradation.lift.network.mapper.toDto
import kotlinx.coroutines.test.runTest
import org.junit.Test


class UpdateRoutineSetRoutineMapperTest {

    @Test
    fun updateRoutineSetRoutineMapper() = runTest {
        with(UPDATE_ROUTINE_SET_ROUTINE_MODEL.toDto()) {
            Truth.assertThat(this.id).isEqualTo(UPDATE_ROUTINE_SET_ROUTINE_REQUEST_DTO.id)
            Truth.assertThat(this.name).isEqualTo(UPDATE_ROUTINE_SET_ROUTINE_REQUEST_DTO.name)
            Truth.assertThat(this.description)
                .isEqualTo(UPDATE_ROUTINE_SET_ROUTINE_REQUEST_DTO.description)
            Truth.assertThat(this.weekday).isEqualTo(UPDATE_ROUTINE_SET_ROUTINE_REQUEST_DTO.weekday)
            Truth.assertThat(this.label).isEqualTo(UPDATE_ROUTINE_SET_ROUTINE_REQUEST_DTO.label)
            Truth.assertThat(this.picture).isEqualTo(UPDATE_ROUTINE_SET_ROUTINE_REQUEST_DTO.picture)
            Truth.assertThat(this.routine).isEqualTo(UPDATE_ROUTINE_SET_ROUTINE_REQUEST_DTO.routine)
        }
    }
}