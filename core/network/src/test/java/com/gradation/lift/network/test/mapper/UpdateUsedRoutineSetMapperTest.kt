package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.Routine.UPDATE_USED_ROUTINE_SET_MODEL
import com.gradation.lift.network.data.TestDtoDataGenerator.Routine.UpdateUsedRoutineSet.UPDATE_USED_ROUTINE_SET_REQUEST_DTO
import com.gradation.lift.network.mapper.toDto
import org.junit.Test


class UpdateUsedRoutineSetMapperTest {

    @Test
    fun updateUsedRoutineSetMapper() {
        with(UPDATE_USED_ROUTINE_SET_MODEL.toDto()) {
            Truth.assertThat(this.routineSetIdList)
                .isEqualTo(UPDATE_USED_ROUTINE_SET_REQUEST_DTO.routineSetIdList)
            Truth.assertThat(this.usedTimeStamp)
                .isEqualTo(UPDATE_USED_ROUTINE_SET_REQUEST_DTO.usedTimeStamp)
        }
    }
}