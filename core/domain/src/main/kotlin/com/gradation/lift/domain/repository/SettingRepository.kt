package com.gradation.lift.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * [SettingRepository]
 * 환경 설정과 관련한 저장소
 * @since 2023-08-28 19:58:58
 */
interface SettingRepository {


    /**
     * [getAutoLoginSetting]
     * 자동 로그인 설정값 불러오기
     * @since 2023-08-28 19:59:08
     */
    fun getAutoLoginSetting(): Flow<Boolean>


    /**
     * [setAutoLoginSetting]
     * 자동 로그인 설정값 설정하기
     * @since 2023-08-28 19:59:27
     */
    suspend fun setAutoLoginSetting(value: Boolean)
}