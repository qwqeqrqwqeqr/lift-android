package com.gradation.lift.network.datasource.inquiry

import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * [InquiryDataSource]
 * 문의하기 관련 데이터 소스
 * @since 2024-02-25 17:06:39
 */
interface InquiryDataSource {

    suspend fun createInquiry(
        content: String,
    ): Flow<NetworkResult<Unit>>


}