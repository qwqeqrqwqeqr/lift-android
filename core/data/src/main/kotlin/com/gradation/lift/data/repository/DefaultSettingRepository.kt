package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.datastore.datasource.SettingDataStoreDataSource
import com.gradation.lift.domain.repository.SettingRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class DefaultSettingRepository @Inject  constructor(
    private val settingDataStoreDataSource: SettingDataStoreDataSource,
    private val dispatcherProvider: DispatcherProvider
) : SettingRepository {


    override fun getAutoLoginSetting(): Flow<Boolean> = flow {
        settingDataStoreDataSource.autoLoginSetting.collect {
            emit(it)
        }
    }.flowOn(dispatcherProvider.default)


    override suspend fun setAutoLoginSetting(value: Boolean) {
        settingDataStoreDataSource.setAutoLoginSetting(value)
    }

}