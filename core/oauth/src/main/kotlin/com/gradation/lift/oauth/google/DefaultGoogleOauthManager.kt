package com.gradation.lift.oauth.google

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.gradation.lift.common.model.DataState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultGoogleOauthManager @Inject constructor(
    @ApplicationContext private val context: Context,
) : GoogleOauthManager {
    override fun getUserId(): Flow<DataState<String>> = flow {
        emit(GoogleSignIn.getLastSignedInAccount(context).let {
            it?.id?.let { id ->
                DataState.Success(id)
            } ?: DataState.Fail("사용자 이메일을 불러올 수 없습니다.")
        })
    }

    override fun getUserEmail(): Flow<DataState<String>> = flow {
        emit(GoogleSignIn.getLastSignedInAccount(context).let {
            it?.email?.let { email ->
                DataState.Success(email)
            } ?: DataState.Fail("사용자 이메일을 불러올 수 없습니다.")
        })
    }


    override fun signOut(): Flow<DataState<Unit>> = flow {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build().apply {
                GoogleSignIn.getClient(context, this).signOut()
                emit(DataState.Success(Unit))
            }

    }
}
