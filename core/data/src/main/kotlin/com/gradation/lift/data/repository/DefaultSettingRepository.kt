package com.gradation.lift.data.repository

import com.gradation.lift.datastore.datasource.SettingDataStoreDataSource
import com.gradation.lift.domain.repository.SettingRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class DefaultSettingRepository @Inject  constructor(
    private val settingDataStoreDataSource: SettingDataStoreDataSource,
) : SettingRepository {


    override fun getAutoLoginSetting(): Flow<Boolean> = flow {
        settingDataStoreDataSource.autoLoginSetting.collect {
            emit(it)
        }
    }


    override suspend fun setAutoLoginSetting(value: Boolean) {
        settingDataStoreDataSource.setAutoLoginSetting(value)
    }

}