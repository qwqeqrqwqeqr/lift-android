package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import kotlinx.coroutines.flow.Flow

/**
 * [InquiryRepository]
 * 문의 관련 저장소
 * @since 2024-02-25 17:09:31
 */
interface InquiryRepository {

    /**
     * [createInquiry]
     * 문의 등록하기
     *  @since 2024-02-25 17:09:53
     */
    fun createInquiry(
        content: String,
    ): Flow<DataState<Unit>>


}