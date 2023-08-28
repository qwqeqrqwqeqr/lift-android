package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.model.user.UserDetail
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
      * [createUserDetail]
      * 사용자의 상세정보를 생성함
      * @since 2023-08-28 20:00:34
      */
     fun createUserDetail(userDetail: UserDetail): Flow<DataState<Boolean>>

     /**
      * [updateUserDetail]
      * 사용자의 상세정보를 갱신함
      * @since 2023-08-28 20:00:59
      */
     fun updateUserDetail(userDetail: UserDetail): Flow<DataState<Boolean>>

     /**
      * [existUserDetail]
      * 사용자의 상세정보가 존재하는지 확인
      * 조건에 따라 상세정보 페이지로 navigate 하기 위해 필요
      */
     fun existUserDetail(): Flow<DataState<Boolean>>

}