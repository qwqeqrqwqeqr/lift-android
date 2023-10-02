package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.model.notification.Notice
import kotlinx.coroutines.flow.Flow

/**
 * [NotificationRepository]
 * 공지사항 및 푸시와 같은 알림 서비스 관련 저장소
 * @since 2023-09-22 16:12:18
 */
interface NotificationRepository {

    /**
     * [getNotice]
     * 전체 공지사항을 불러옵니다.
     * @since 2023-08-16 11:40:20
     */
    fun getNotice(): Flow<DataState<List<Notice>>>

  
}