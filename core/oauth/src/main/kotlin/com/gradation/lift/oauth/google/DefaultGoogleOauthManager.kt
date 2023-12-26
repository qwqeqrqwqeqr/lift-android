package com.gradation.lift.oauth.google

import com.gradation.lift.common.model.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultGoogleOauthManager @Inject constructor() : GoogleOauthManager {
    override fun getUserId(): Flow<DataState<String>> = flow {
        //TODO Not Implement
    }

    override fun getUserEmail(): Flow<DataState<String>> = flow {
        //TODO Not Implement
    }


    override fun signOut(): Flow<DataState<Unit>> = flow {
       //TODO Not Implement
    }
}