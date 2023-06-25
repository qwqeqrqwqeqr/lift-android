package com.gradation.lift.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.gradation.lift.datastore.Constants.ACCESS_TOKEN
import com.gradation.lift.datastore.Constants.REFRESH_TOKEN
import com.gradation.lift.datastore.Constants.USER_ID
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject


class DataStoreDataSource @Inject constructor(
    private val accountPreferences: DataStore<Preferences>,
) {
    suspend fun setUserId(userId: String) {
        accountPreferences.edit { preferences -> preferences[USER_ID] = userId }
    }

    val userId: Flow<String> = flow {
        accountPreferences.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map {emit(it[USER_ID] ?: "") }
    }

    val accessToken: Flow<String> = flow {
        accountPreferences.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { emit(it[ACCESS_TOKEN] ?: "") }
    }

    suspend fun setAccessToken(accessToken: String) {
        accountPreferences.edit { preferences -> preferences[ACCESS_TOKEN] = accessToken }
    }

    val refreshToken: Flow<String> = flow {
        accountPreferences.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { emit(it[REFRESH_TOKEN] ?: "") }
    }

    suspend fun setRefreshToken(refreshToken: String) {
        accountPreferences.edit { preferences -> preferences[REFRESH_TOKEN] = refreshToken }
    }

}


