package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.inquiry.CreateInquiryRequestDto
import com.gradation.lift.network.dto.inquiry.CreateInquiryResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * [InquiryService]
 * 문의하기 서비스
 * @since 2024-02-25 17:02:19
 */
interface InquiryService {

    /**
     * [createInquiry]
     * 문의 사항 등록하기
     * @since 2024-02-25 17:04:59
     */
    @POST("inquiry/inquiry")
    suspend fun createInquiry(@Body createInquiryRequestDto: CreateInquiryRequestDto): Response<APIResultWrapper<CreateInquiryResponseDto>>

}