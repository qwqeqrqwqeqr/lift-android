package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.NoticeRepository
import com.gradation.lift.model.model.notification.Notice
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.notice.NoticeDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class DefaultNoticeRepository @Inject constructor(
    private val noticeDataSource: NoticeDataSource,
    private val dispatcherProvider: DispatcherProvider
) : NoticeRepository {
    override fun getNotice(): Flow<DataState<List<Notice>>> = flow {
        noticeDataSource.getNotice().distinctUntilChanged().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun getNoticeById(noticeId: Int): Flow<DataState<Notice>> = flow {
        noticeDataSource.getNoticeById(noticeId).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

}