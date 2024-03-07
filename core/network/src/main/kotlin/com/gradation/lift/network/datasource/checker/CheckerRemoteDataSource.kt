package com.gradation.lift.network.datasource.checker

import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * [CheckerRemoteDataSource]
 * 유효성 검증과 관련한 데이터 소스
 * @since 2023-08-28 22:07:49
 */
interface CheckerRemoteDataSource {
    suspend fun checkDuplicateEmail(email: String): Flow<NetworkResult<Boolean>>
    suspend fun checkDuplicateName(name: String): Flow<NetworkResult<Boolean>>
}