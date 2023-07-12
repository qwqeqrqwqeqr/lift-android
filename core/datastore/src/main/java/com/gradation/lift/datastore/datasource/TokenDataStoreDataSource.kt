package com.gradation.lift.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.gradation.lift.datastore.Constants.ACCESS_TOKEN
import com.gradation.lift.datastore.Constants.REFRESH_TOKEN
import com.gradation.lift.datastore.di.TokenPreferences
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


    suspend fun clearAll() {
        dataStore.edit {
            it.clear()
        }
    }


    companion object {
        const val EMPTY_VALUE = ""
    }
}


