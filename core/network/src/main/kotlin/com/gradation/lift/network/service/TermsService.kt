package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.terms.CreateUserTermsConsentRequestDto
import com.gradation.lift.network.dto.terms.CreateUserTermsConsentResponseDto
import com.gradation.lift.network.dto.terms.GetUserMarketingTermsConsentResponseDto
import com.gradation.lift.network.dto.terms.UpdateUserMarketingTermsConsentRequestDto
import com.gradation.lift.network.dto.terms.UpdateUserMarketingTermsConsentResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT

/**
 * [TermsService]
 * 이용약관 서비스
 * @since 2023-12-28 17:34:26
 */
interface TermsService {
    /**
     * [createUserTermsConsent]
     * 사용자 이용약관 동의
     * @since 2023-12-28 17:32:53
     */
    @POST("terms/consent/terms")
    suspend fun createUserTermsConsent(@Body createUserTermsConsentRequestDto: CreateUserTermsConsentRequestDto): Response<APIResultWrapper<CreateUserTermsConsentResponseDto>>

    /**
     * [getUserMarketingTermsConsent]
     * 마케팅 이용약관 동의 여부 불러오기
     * @since 2023-12-28 17:32:53
     */
    @GET("terms/consent/marketing-terms")
    suspend fun getUserMarketingTermsConsent(): Response<APIResultWrapper<GetUserMarketingTermsConsentResponseDto>>

    /**
     * [updateUserMarketingTermsConsent]
     * 마케팅 이용약관 동의 여부 수정하기
     * @since 2023-12-28 17:32:53
     */
    @PATCH("terms/consent/marketing-terms")
    suspend fun updateUserMarketingTermsConsent(@Body updateUserMarketingTermsConsentRequestDto: UpdateUserMarketingTermsConsentRequestDto): Response<APIResultWrapper<UpdateUserMarketingTermsConsentResponseDto>>

}



