package com.gradation.lift.network.datasource.notice

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.model.model.notice.Notice
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.NoticeService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultNoticeDefaultRemoteDataSource @Inject constructor(
    private val noticeService: NoticeService,
    private val networkResultHandler: NetworkResultHandler,
    private val dispatcherProvider: DispatcherProvider,
) : NoticeRemoteDataSource {
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
        }.flowOn(dispatcherProvider.default)

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
        }.flowOn(dispatcherProvider.default)
}