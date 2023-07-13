package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.routine.CreateRoutineSetRoutine
import kotlinx.coroutines.flow.Flow

interface SettingRepository {


    fun getAutoLoginSetting(): Flow<Boolean>


    suspend fun setAutoLoginSetting(value: Boolean)
}