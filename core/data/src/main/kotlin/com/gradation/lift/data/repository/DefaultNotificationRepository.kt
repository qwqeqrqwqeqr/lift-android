package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.NotificationRepository
import com.gradation.lift.model.model.notification.Notice
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.notification.NotificationDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class DefaultNotificationRepository @Inject constructor(
    private val notificationDataSource: NotificationDataSource,
) : NotificationRepository {
    override fun getNotice(): Flow<DataState<List<Notice>>> = flow {
        notificationDataSource.getNotice().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }

}