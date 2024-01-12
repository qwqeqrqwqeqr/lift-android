package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.model.auth.LoginMethod
import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.model.model.user.UserDetailInfo
import com.gradation.lift.model.model.user.UserDetailName
import com.gradation.lift.model.model.user.UserDetailProfilePicture
import kotlinx.coroutines.flow.Flow


/**
 *  [UserRepository]
 *  사용자 정보를 관리하는 저장소
 *  @since 2023-08-28 20:00:06
 */
interface UserRepository {

    /**
     * [getUserDetail]
     * 사용자의 상세정보를 불러옴
     * @since 2023-08-28 20:00:19
     */
    fun getUserDetail(): Flow<DataState<UserDetail>>


    /**
     * [getUserAuthenticationMethod]
     * 사용자의 로그인 방식을 불러옵니다.
     * @since 2024-01-12 12:32:47
     */
    fun getUserAuthenticationMethod(): Flow<DataState<LoginMethod>>


    /**
     * [existUserDetail]
     * 사용자의 상세정보가 존재하는지 확인
     * 조건에 따라 상세정보 페이지로 navigate 하기 위해 필요
     * @since 2024-01-12 12:33:46
     */
    fun existUserDetail(): Flow<DataState<Boolean>>

    /**
     * [createUserDetail]
     * 사용자의 상세정보를 생성함
     * @since 2023-08-28 20:00:34
     */
    fun createUserDetail(userDetail: UserDetail): Flow<DataState<Unit>>

    /**
     * [updateUserDetail]
     * 사용자의 상세정보를 갱신함
     * @since 2023-08-28 20:00:59
     */
    fun updateUserDetail(userDetail: UserDetail): Flow<DataState<Unit>>


    /**
     * [updateUserDetailProfilePicture]
     * 사용자의 프로필 사진을 갱신함
     * @since 2023-08-28 20:00:59
     */
    fun updateUserDetailProfilePicture(userDetailProfilePicture: UserDetailProfilePicture): Flow<DataState<Unit>>

    /**
     * [updateUserDetailName]
     * 사용자의 이름 갱신함
     * @since 2024-01-12 12:34:14
     */
    fun updateUserDetailName(userDetailName: UserDetailName): Flow<DataState<Boolean>>

    /**
     * [updateUserDetailInfo]
     * 사용자의 상세정보 갱신 (성별,키,몸무게)
     * @since 2024-01-12 12:34:35
     */
    fun updateUserDetailInfo(userDetailInfo: UserDetailInfo): Flow<DataState<Boolean>>

}