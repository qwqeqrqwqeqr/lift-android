package com.gradation.lift.database.datasource.notice

import com.gradation.lift.model.model.notification.Notice
import kotlinx.coroutines.flow.Flow

/**
 * [getAllNotice] 모든 공지사항 불러오기, Boolean은 읽었는지 여부를 의미함
 * [deleteAllNotice] 모든 공지사항 삭제
 * [insertAllNotice] 공지사항 추가
 * [updateNoticeChecked] 공지사항 업데이트 (읽음 여부 업데이트를 위해 사용)
 * @since 2023-10-15 18:21:58
 */
interface NoticeDataSource {
    suspend fun getAllNotice(): Flow<List<Pair<Notice, Boolean>>>
    suspend fun deleteAllNotice()
    suspend fun insertAllNotice(notice: List<Notice>)
    suspend fun updateNoticeChecked(notice: Notice)

    suspend fun fetchNotice(notice: List<Notice>)
}