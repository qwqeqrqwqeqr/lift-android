package com.gradation.lift.network.datasource.naver

import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

interface NaverDataSource {

    fun signIn() : Flow<NetworkResult<String>>

}