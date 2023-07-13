package com.gradation.lift.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore


internal object Constants {





    const val TOKEN_PREFERENCES = "token_preferences"
    val ACCESS_TOKEN = stringPreferencesKey("access_token")
    val REFRESH_TOKEN = stringPreferencesKey("refresh_token")



    const val SETTING_PREFERENCES = "setting_preferences"
    val AUTO_LOGIN = booleanPreferencesKey("auto_login")


}

