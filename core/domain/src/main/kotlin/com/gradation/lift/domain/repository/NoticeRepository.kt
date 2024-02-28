package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.model.notice.Notice
import kotlinx.coroutines.flow.Flow

/**
 * [NoticeRepository]
 * 공지사항 및 푸시와 같은 알림 서비스 관련 저장소
 * @since 2023-09-22 16:12:18
 */
interface NoticeRepository {

    /**
     * [getNotice]
     * 전체 공지사항을 불러옵니다.
     * @since 2023-08-16 11:40:20
     */
    fun getNotice(): Flow<DataState<List<Notice>>>



    /**
     * [getNoticeById]
     * 공지사항 아이디에 맞는 공지사항을 불러옵니다.
     * @since 2024-01-12 12:30:56
     */
    fun getNoticeById(noticeId: Int): Flow<DataState<Notice>>
}