package com.gradation.lift.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.gradation.lift.datastore.Constants.ACCESS_TOKEN
import com.gradation.lift.datastore.Constants.REFRESH_TOKEN
import com.gradation.lift.datastore.Constants.USER_ID
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject


class DataStoreDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    suspend fun setUserId(userId: String) {
        dataStore.edit { preferences -> preferences[USER_ID] = userId }
    }

    suspend fun setAccessToken(accessToken: String) {
        dataStore.edit { preferences -> preferences[ACCESS_TOKEN] = accessToken }
    }

    suspend fun setRefreshToken(refreshToken: String) {
        dataStore.edit { preferences -> preferences[REFRESH_TOKEN] = refreshToken }
    }


    val userId: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emptyPreferences()
            } else {
                throw exception
            }
        }
        .map { it[USER_ID] ?: "" }


    val accessToken: Flow<String> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emptyPreferences()
                } else {
                    throw exception
                }
            }
            .map { it[ACCESS_TOKEN] ?: "" }

    val refreshToken =
        dataStore.data.map { it[REFRESH_TOKEN] ?: "" }.catch { exception ->
            if (exception is IOException) {
                emptyPreferences()
            } else {
                throw exception
            }

        }


    suspend fun clearAll() {
        dataStore.edit {
            it.clear()
        }
    }


}


