package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.user.*
import retrofit2.Response
import retrofit2.http.*

/**
 * [UserService]
 * 사용자 정보 서비스
 * @since 2023-08-28 22:30:56
 */
interface UserService {

    /**
     * [getUserDetail]
     * 사용자 상세정보 불러오기
     * @since 2023-08-28 22:30:51
     */
    @GET("user/user-detail")
    suspend fun getUserDetail(): Response<APIResultWrapper<GetUserDetailResponseDto>>


    @GET("user/authentication-method")
    suspend fun getUserAuthenticationMethod(): Response<APIResultWrapper<GetUserAuthenticationMethodResponseDto>>


    /**
     * [createUserDetail]
     * 사용자 상세정보 생성하기
     * @since 2023-08-28 22:30:46
     */
    @POST("user/user-detail")
    suspend fun createUserDetail(
        @Body createUserDetailRequestDto: CreateUserDetailRequestDto,
    ): Response<APIResultWrapper<CreateUserDetailResponseDto>>

    /**
     * [updateUserDetail]
     * 사용자 상세정보 업데이트하기
     * @since 2023-08-28 22:30:41
     */
    @PUT("user/user-detail")
    suspend fun updateUserDetail(@Body updateUserDetailRequestDto: UpdateUserDetailRequestDto): Response<APIResultWrapper<UpdateUserDetailResponseDto>>

    /**
     * [updateUserDetailProfilePicture]
     * 사용자 상세정보 업데이트하기
     * @since 2023-10-06 21:52:40
     */
    @PATCH("user/user-detail/profile-picture")
    suspend fun updateUserDetailProfilePicture(@Body updateUserDetailProfilePictureRequestDto: UpdateUserDetailProfilePictureRequestDto):
            Response<APIResultWrapper<UpdateUserDetailProfilePictureResponseDto>>


    /**
     * [updateUserDetailName]
     * 사용자 이름 업데이트하기
     * @since 2024-01-11 17:19:34
     */
    @PATCH("user/user-detail/name")
    suspend fun updateUserDetailName(@Body updateUserDetailNameRequestDto: UpdateUserDetailNameRequestDto):
            Response<APIResultWrapper<UpdateUserDetailNameResponseDto>>


    /**
     * [updateUserDetailInfo]
     * 사용자 정보(성별,키,몸무게) 업데이트하기
     * @since 2024-01-11 17:19:37
     */
    @PATCH("user/user-detail/info")
    suspend fun updateUserDetailInfo(@Body updateUserDetailInfoRequestDto: UpdateUserDetailInfoRequestDto):
            Response<APIResultWrapper<UpdateUserDetailInfoResponseDto>>


    /**
     * [createUserDetail]
     * 사용자의 상세정보가 존재하는지 확인하기
     * @since 2023-08-28 22:30:37
     */
    @GET("user/exist-user-detail")
    suspend fun existUserDetail(): Response<APIResultWrapper<ExistUserDetailResponseDto>>


    /**
     * [deleteUser]
     * 사용자 삭제
     * @since 2024-02-19 15:07:54
     */
    @POST("user")
    suspend fun deleteUser(@Body deleteUserRequestDto: DeleteUserRequestDto): Response<APIResultWrapper<DeleteUserResponseDto>>
}