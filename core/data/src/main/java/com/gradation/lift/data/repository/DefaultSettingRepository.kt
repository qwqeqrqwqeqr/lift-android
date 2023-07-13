package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.datastore.datasource.SettingDataStoreDataSource
import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.network.common.toMessage
import com.gradation.lift.domain.repository.CheckerRepository
import com.gradation.lift.domain.repository.SettingRepository
import com.gradation.lift.model.user.Email
import com.gradation.lift.model.user.Name
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.CheckerDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class DefaultSettingRepository @Inject constructor(
    private val settingDataStoreDataSource: SettingDataStoreDataSource,
) : SettingRepository {


    override suspend fun getAutoLoginSetting(): Flow<Boolean> = settingDataStoreDataSource.autoLoginSetting


    override suspend fun setAutoLoginSetting(value: Boolean) {
        settingDataStoreDataSource.setAutoLoginSetting(value)
    }

}