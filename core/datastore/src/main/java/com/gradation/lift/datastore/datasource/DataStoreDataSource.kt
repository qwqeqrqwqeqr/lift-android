package com.gradation.lift.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.gradation.lift.datastore.datasource.DataStoreConstants.USER_ID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


class DataStoreDataSource @Inject constructor(
    private val userPreferences: DataStore<Preferences>,
) {
    suspend fun setUserId(userId: String) {
        userPreferences.edit { preferences -> preferences[USER_ID] = userId }
    }

    val userId: Flow<String> = userPreferences.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { it[USER_ID] ?: "" }

}


internal object DataStoreConstants {
    val USER_ID = stringPreferencesKey("user_id")
    const val USER_PREFERENCES = "user_preferences"
}