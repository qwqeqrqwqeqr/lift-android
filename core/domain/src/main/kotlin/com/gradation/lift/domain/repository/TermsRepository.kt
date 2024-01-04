package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import kotlinx.coroutines.flow.Flow

/**
 * [TermsRepository]
 *  * 이용약관 저장소
 * @since 2023-12-28 17:52:43
 */
interface TermsRepository {

    /**
     * [createUserTermsConsent]
     * 사용자에 대한 약관 동의 정보 저장
     *  @since 2023-12-28 17:52:30
     */
    fun createUserTermsConsent(
        consent: Boolean,
        marketingConsent: Boolean,
    ): Flow<DataState<Boolean>>

    /**
     * [getUserMarketingTermsConsent]
     * 마케팅 약관 동의 정보 불러오기
     * @since 2023-12-28 17:53:19
     */
    fun getUserMarketingTermsConsent(): Flow<DataState<Boolean>>

    /**
     * [updateUserMarketingTermsConsent]
     * 마케팅 약관 동의 정보 수정하기
     * @since 2023-12-28 17:53:19
     */
    fun updateUserMarketingTermsConsent(marketingConsent: Boolean): Flow<DataState<Boolean>>
}