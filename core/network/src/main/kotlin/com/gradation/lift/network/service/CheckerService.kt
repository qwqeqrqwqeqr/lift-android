package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.checker.CheckDuplicateEmailResponseDto
import com.gradation.lift.network.dto.checker.CheckDuplicateNameResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * [CheckerService]
 * 유효성 검증 서비스
 * @since 2023-08-28 22:32:40
 */
interface CheckerService {


    /**
     * [checkDuplicateEmail]
     * 이메일 중복 확인
     * @since 2023-08-28 22:32:34
     */
    @GET("checker/duplicate-email")
    suspend fun checkDuplicateEmail(
        @Query(
            "email",
            encoded = true
        ) email: String,
    ): Response<APIResultWrapper<CheckDuplicateEmailResponseDto>>


    /**
     *  [checkDuplicateName]
     *  이름 중복 확인
     *  @since 2023-08-28 22:32:30
     */
    @GET("checker/duplicate-name")
    suspend fun checkDuplicateName(
        @Query(
            "name",
            encoded = true
        ) name: String,
    ): Response<APIResultWrapper<CheckDuplicateNameResponseDto>>

}