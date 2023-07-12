package com.gradation.lift.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.gradation.lift.datastore.Constants.AUTO_LOGIN
import com.gradation.lift.datastore.di.SettingPreferences
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject


class SettingDataStoreDataSource @Inject constructor(
    @SettingPreferences private val dataStore: DataStore<Preferences>,
) {


    suspend fun setAutoLogin(autoLogin: Boolean) {
        dataStore.edit { preferences -> preferences[AUTO_LOGIN] = autoLogin }
    }


    val autoLogin: Flow<Boolean> =
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


