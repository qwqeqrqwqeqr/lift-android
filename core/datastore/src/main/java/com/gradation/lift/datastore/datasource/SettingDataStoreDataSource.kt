package com.gradation.lift.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.gradation.lift.datastore.Constants.AUTO_LOGIN
import com.gradation.lift.datastore.Constants.LOGIN_METHOD
import com.gradation.lift.datastore.di.SettingPreferences
import com.gradation.lift.model.model.auth.LoginMethod
import com.gradation.lift.model.model.auth.toLoginMethod
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject


class SettingDataStoreDataSource @Inject constructor(
    @SettingPreferences private val dataStore: DataStore<Preferences>,
) {


    suspend fun setAutoLoginSetting(autoLogin: Boolean) {
        dataStore.edit { preferences -> preferences[AUTO_LOGIN] = autoLogin }
    }

    suspend fun setLoginMethod(loginMethod: LoginMethod) {
        dataStore.edit { preferences -> preferences[LOGIN_METHOD] = loginMethod.getValue() }
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


    val loginMethod: Flow<LoginMethod> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emptyPreferences()
                } else {
                    throw exception
                }
            }
            .map { it[LOGIN_METHOD].toLoginMethod() }


    suspend fun clearAll() {
        dataStore.edit {
            it.clear()
        }
    }


    companion object {
        const val EMPTY_VALUE = false
    }
}


