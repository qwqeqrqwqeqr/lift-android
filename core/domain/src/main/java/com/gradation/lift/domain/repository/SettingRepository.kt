package com.gradation.lift.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingRepository {


    fun getAutoLoginSetting(): Flow<Boolean>


    suspend fun setAutoLoginSetting(value: Boolean)
}