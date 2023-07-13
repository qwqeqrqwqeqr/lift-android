package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.routine.CreateRoutineSetRoutine
import kotlinx.coroutines.flow.Flow

interface SettingRepository {


    suspend fun getAutoLogin(): Flow<Boolean>


    suspend fun setAutoLogin(value: Boolean)
}