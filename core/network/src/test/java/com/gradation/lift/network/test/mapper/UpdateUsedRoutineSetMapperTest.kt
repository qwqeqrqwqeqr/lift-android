package com.gradation.lift.network.test.mapper

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.updateUsedRoutineSetModel
import com.gradation.lift.network.data.TestDtoDataGenerator.RoutineSet.updateUsedRoutineSetRequestDto
import com.gradation.lift.network.mapper.toDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class UpdateUsedRoutineSetMapperTest {

    @Test
    fun updateUsedRoutineSetMapper() = runTest {
        with(updateUsedRoutineSetModel.toDto()) {
            Truth.assertThat(this.routineSetIdList)
                .isEqualTo(updateUsedRoutineSetRequestDto.routineSetIdList)
        }
    }
}