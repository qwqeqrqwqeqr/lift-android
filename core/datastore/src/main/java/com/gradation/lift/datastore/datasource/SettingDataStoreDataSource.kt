package com.gradation.lift.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.gradation.lift.datastore.Constants.AUTO_LOGIN
import com.gradation.lift.datastore.di.SettingPreferences
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

/**
 * [SettingDataStoreDataSource]
 * 사용자에 대한 설정 값들을 관리하는 DataStore
 * @since 2023-08-28 22:43:38
 */
class SettingDataStoreDataSource @Inject constructor(
    @SettingPreferences private val dataStore: DataStore<Preferences>,
) {


    suspend fun setAutoLoginSetting(autoLogin: Boolean) {
        dataStore.edit { preferences -> preferences[AUTO_LOGIN] = autoLogin }
    }



    val autoLoginSetting: Flow<Boolean> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emptyPreferences()
                } else {
                    throw exception
                }
            }
            .map { it[AUTO_LOGIN] ?: EMPTY_VALUE }




    suspend fun clearAll() {
        dataStore.edit {
            it.clear()
        }
    }


    companion object {
        const val EMPTY_VALUE = false
    }
}


