package com.gradation.lift.network.datasource.user

import com.gradation.lift.model.model.auth.LoginMethod
import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.model.model.user.UserDetailInfo
import com.gradation.lift.model.model.user.UserDetailName
import com.gradation.lift.model.model.user.UserDetailProfilePicture
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * [UserDataSource]
 * 사용자 정보와 관련한 데이터 소스
 * @since 2023-08-28 22:09:31
 */
interface UserDataSource {
    suspend fun getUserDetail(): Flow<NetworkResult<UserDetail>>
    suspend fun getUserAuthenticationMethod(): Flow<NetworkResult<LoginMethod>>

    suspend fun createUserDetail(userDetail: UserDetail): Flow<NetworkResult<Unit>>

    suspend fun updateUserDetail(userDetail: UserDetail): Flow<NetworkResult<Unit>>

    suspend fun updateUserDetailProfilePicture(userDetailProfilePicture: UserDetailProfilePicture): Flow<NetworkResult<Unit>>

    suspend fun existUserDetail(): Flow<NetworkResult<Boolean>>

    suspend fun updateUserDetailName(userDetailName: UserDetailName): Flow<NetworkResult<Boolean>>
    suspend fun updateUserDetailInfo(userDetailInfo: UserDetailInfo): Flow<NetworkResult<Boolean>>
}