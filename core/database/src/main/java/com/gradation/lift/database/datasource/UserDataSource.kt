package com.gradation.lift.database.datasource

import com.gradation.lift.model.model.user.UserDetail
import kotlinx.coroutines.flow.Flow

/**
 * [getUser] 사용자 정보 불러오기
 * [deleteAllUser] 저장되어있는 모든 사용자 정보 삭제
 * [insertUser] 사용자 추가
 * @since 2023-10-15 18:33:03
 */
interface UserDataSource {
    suspend fun getUser(): Flow<UserDetail>
    suspend fun deleteAllUser()
    suspend fun insertUser(userDetail: UserDetail)
}