package com.gradation.lift.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.gradation.lift.datastore.Constants
import com.gradation.lift.datastore.Constants.ACCESS_TOKEN
import com.gradation.lift.datastore.Constants.LOGIN_METHOD
import com.gradation.lift.datastore.Constants.REFRESH_TOKEN
import com.gradation.lift.datastore.di.TokenPreferences
import com.gradation.lift.model.model.auth.LoginMethod
import com.gradation.lift.model.model.auth.toLoginMethod
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject


class TokenDataStoreDataSource @Inject constructor(
    @TokenPreferences private val dataStore: DataStore<Preferences>,
) {


    suspend fun setAccessToken(accessToken: String) {
        dataStore.edit { preferences -> preferences[ACCESS_TOKEN] = accessToken }
    }

    suspend fun setRefreshToken(refreshToken: String) {
        dataStore.edit { preferences -> preferences[REFRESH_TOKEN] = refreshToken }
    }

    suspend fun setLoginMethod(loginMethod: LoginMethod) {
        dataStore.edit { preferences -> preferences[LOGIN_METHOD] = loginMethod.toValue() }
    }


    val accessToken: Flow<String> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emptyPreferences()
                } else {
                    throw exception
                }
            }
            .map { it[ACCESS_TOKEN] ?: EMPTY_VALUE }

    val refreshToken =
        dataStore.data.map { it[REFRESH_TOKEN] ?: EMPTY_VALUE }.catch { exception ->
            if (exception is IOException) {
                emptyPreferences()
            } else {
                throw exception
            }
        }

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
        const val EMPTY_VALUE = ""
    }
}


