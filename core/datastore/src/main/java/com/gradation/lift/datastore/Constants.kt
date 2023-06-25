package com.gradation.lift.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

internal object Constants {
    val USER_ID = stringPreferencesKey("user_id")
    val ACCESS_TOKEN = stringPreferencesKey("access_token")
    val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    const val ACCOUNT_PREFERENCES = "account_preferences"
}
