package com.gradation.lift.database.datasource.notice

import com.gradation.lift.database.dao.NoticeDao
import com.gradation.lift.database.mapper.toEntity
import com.gradation.lift.model.model.notification.Notice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultNoticeDataSource @Inject constructor(
    private val noticeDao: NoticeDao,
) : NoticeDataSource {
    override suspend fun getAllNotice(): Flow<List<Pair<Notice, Boolean>>> = flow {
        noticeDao.getAllNotice().collect { noticeEntity ->
            emit(noticeEntity.map { Pair(it.toDomain(), it.checked) })
        }
    }

    override suspend fun deleteAllNotice() {
        noticeDao.deleteAllNotice()
    }

    override suspend fun insertAllNotice(notice: List<Notice>) {
        noticeDao.insertAllNotice(*notice.map { it.toEntity() }.toTypedArray())
    }

    override suspend fun updateNoticeChecked(notice: Notice) {
        noticeDao.updateNotice(notice.toEntity().copy(checked = true))
    }

    override suspend fun fetchNotice(notice: List<Notice>) {
        noticeDao.getAllNotice().collect {
            val checkedList = it.filter { noticeEntity -> noticeEntity.checked }.map { it.id }
            noticeDao.deleteAllNotice()
            noticeDao.insertAllNotice(
                *notice.map { notice ->
                    notice
                        .toEntity()
                        .copy(checked = checkedList.contains(notice.id))
                }.toTypedArray()
            )
        }
    }


}