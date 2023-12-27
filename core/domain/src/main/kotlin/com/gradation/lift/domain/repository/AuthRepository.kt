package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import com.gradation.lift.model.model.auth.LoginMethod
import kotlinx.coroutines.flow.Flow

/**
 * [AuthRepository]
 * 사용자의 계정 및 토큰을 관리하는 저장소
 * @since 2023-08-16 11:35:24
 */
interface AuthRepository {

    /**
     * [signInDefault]
     * 일반적인 방식으로 로그인 시 사용
     * @param signInInfo 아이디와 패스워드를 저장하고 있는 클래스
     * @since 2023-08-16 11:40:20
     */
    fun signInDefault(signInInfo: DefaultSignInInfo): Flow<DataState<Unit>>

    /**
     * [signUpDefault]
     * 일반적인 방식으로 회원 가입 시 사용
     * @param signUpInfo 회원가입 시에 필요한 데이터로 서버에 전달됨
     * @since 2023-08-16 11:40:15
     */
    fun signUpDefault(signUpInfo: DefaultSignUpInfo): Flow<DataState<Boolean>>

    /**
     * [signInNaver]
     * 네이버를 통한 로그인 시 사용
     * 서버 내에 회원 정보 저장하며 로그인을 동시에 진행˚
     * @since 2023-08-16 11:40:09
     */
    fun signInNaver(): Flow<DataState<Unit>>

    /**
     * [signUpNaver]
     * 네이버를 통한 회원가입
     * @since 2023-12-27 15:04:45
     */
    fun signUpNaver(): Flow<DataState<Boolean>>

    /**
     * [signInKakao]
     * 카카오를 통한 로그인 시 사용
     * 서버 내에 회원 정보 저장하며 로그인을 동시에 진행
     * @since 2023-08-16 11:40:03
     */
    fun signInKakao(): Flow<DataState<Unit>>


    /**
     * [signUpKakao]
     * 카카오를 통한 회원가입
     * @since 2023-12-27 15:04:45
     */
    fun signUpKakao(): Flow<DataState<Boolean>>


    /**
     * [signInGoogle]
     * 카카오를 통한 로그인 시 사용
     * 서버 내에 회원 정보 저장하며 로그인을 동시에 진행
     * @since 2023-12-26 18:38:52
     */
    fun signInGoogle(): Flow<DataState<Unit>>

    /**
     * [signUpGoogle]
     * 구글을 통한 회원가입
     * @since 2023-12-27 15:04:45
     */
    fun signUpGoogle(): Flow<DataState<Boolean>>

    /**
     * [checkExistUser]
     * 서버에 사용자의 아이디가 존재하는 지 확인
     * @since 2023-12-26 18:38:52
     */
    fun checkExistUser(userId: String): Flow<DataState<Boolean>>

    /**
     * [getLoginMethod]
     * 현재 어떠한 방식으로 로그인 했는지 확인
     * @since 2023-08-16 11:39:55
     */
    fun getLoginMethod(): Flow<DataState<LoginMethod>>

    /**
     * [isSigned]
     * 현재 로그인이 되어 있는 상태인지 확인
     * @since 2023-08-16 11:39:49
     */
    fun isSigned(): Flow<DataState<Boolean>>

    /**
     * [signOut]
     * 로그아웃
     * 클라이언트 기기에 저장되어있는 모든 토큰정보 삭제
     * @since 2023-08-16 11:39:41
     */
    fun signOut(): Flow<DataState<Unit>>
}