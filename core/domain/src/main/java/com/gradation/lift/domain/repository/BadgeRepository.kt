package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.model.badge.Badge
import com.gradation.lift.model.model.badge.BadgeCondition
import com.gradation.lift.model.model.badge.CreateUserBadge
import com.gradation.lift.model.model.badge.UserBadge
import kotlinx.coroutines.flow.Flow

/**
 * [BadgeRepository]
 * 뱃지 서비스 관련 저장소
 * @since 2023-09-22 16:11:35
 */
interface BadgeRepository {


    /**
     * [getBadge]
     * 모든 뱃지를 불러옵니다.
     * @since 2023-09-25 17:38:01
     */
    fun getBadge(): Flow<DataState<List<Badge>>>

    /**
     * [getUserBadge]
     * 사용자가 획득한 뱃지를 불러옵니다.
     * @since 2023-09-25 18:07:46
     */
    fun getUserBadge(): Flow<DataState<List<UserBadge>>>

    /**
     * [createUserBadge]
     * 뱃지를 획득합니다.
     * @since 2023-09-25 18:08:25
     */
    fun createUserBadge(createUserBadge: CreateUserBadge): Flow<DataState<Boolean>>

    /**
     * [getUserBadgeByMainFlag]
     * 사용자가 설정한 대표 뱃지들을 불러옵니다.
     * @since 2023-09-25 18:10:14
     */
    fun getUserBadgeByMainFlag(): Flow<DataState<List<UserBadge>>>


    /**
     * [getUserBadgeByCondition]
     * 뱃지 획득 조건을 만족하는 뱃지를 불러옵니다.
     * @since 2023-09-25 18:10:14
     */
    fun getUserBadgeByCondition(): Flow<DataState<BadgeCondition>>


}