package com.gradation.lift.network.datasource.kakao

import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

interface KakaoDataSource {

    fun signIn() : Flow<NetworkResult<String>>

}