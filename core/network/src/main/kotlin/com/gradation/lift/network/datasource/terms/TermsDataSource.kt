package com.gradation.lift.network.datasource.terms

import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow
/**
 * [TermsDataSource]
 * 인증 인가와 관련된 데이터 소스
 * @since 2023-08-28 22:06:44
 */
interface TermsDataSource {

    suspend fun createUserTermsConsent(
        consent: Boolean,
        marketingConsent: Boolean,
    ): Flow<NetworkResult<Boolean>>

    suspend fun getUserMarketingTermsConsent(): Flow<NetworkResult<Boolean>>
    suspend fun updateUserMarketingTermsConsent(marketingConsent: Boolean): Flow<NetworkResult<Boolean>>
}