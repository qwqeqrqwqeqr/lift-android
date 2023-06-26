package com.gradation.lift.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

internal object Constants {

    const val LIFT_PREFERENCES = "lift_datastore"
    val USER_ID = stringPreferencesKey("user_id")
    val ACCESS_TOKEN = stringPreferencesKey("access_token")
    val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
}
