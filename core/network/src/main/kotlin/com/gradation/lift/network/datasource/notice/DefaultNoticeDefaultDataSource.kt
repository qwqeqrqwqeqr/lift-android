package com.gradation.lift.network.datasource.notice

import com.gradation.lift.model.model.notification.Notice
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.NoticeService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultNoticeDefaultDataSource @Inject constructor(
    private val noticeService: NoticeService,
    private val networkResultHandler: NetworkResultHandler,
) : NoticeDataSource {
    override suspend fun getNotice(): Flow<NetworkResult<List<Notice>>> =
        flow {
            networkResultHandler {
                noticeService.getNotice()
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }

    override suspend fun getNoticeById(noticeId: Int): Flow<NetworkResult<Notice>> =
        flow {
            networkResultHandler {
                noticeService.getNoticeById(noticeId = noticeId)
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }
}