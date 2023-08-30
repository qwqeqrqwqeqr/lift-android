package com.gradation.lift.data.fake.oauthmanager

import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.oauth.naver.NaverOauthManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNaverOauthManager (private val testReturnState: TestReturnState = TestReturnState.Success) :
    NaverOauthManager {
    override fun getUserId(): Flow<DataState<String>> = flow{
        when(testReturnState){
            TestReturnState.Fail -> emit(DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE))
            TestReturnState.Success ->emit( DataState.Success(DefaultDataGenerator.FAKE_STRING_DATA))
        }
    }

    override fun getUserEmail(): Flow<DataState<String>>  = flow{
        when(testReturnState){
            TestReturnState.Fail -> emit(DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE))
            TestReturnState.Success ->emit( DataState.Success(DefaultDataGenerator.FAKE_STRING_DATA))
        }
    }

    override fun signOut(){}
}