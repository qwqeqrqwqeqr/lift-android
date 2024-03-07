package com.gradation.lift.network.datasource.notice

import com.gradation.lift.model.model.notice.Notice
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

interface NoticeRemoteDataSource {


    suspend fun getNotice(): Flow<NetworkResult<List<Notice>>>
    suspend fun getNoticeById(noticeId: Int): Flow<NetworkResult<Notice>>
}