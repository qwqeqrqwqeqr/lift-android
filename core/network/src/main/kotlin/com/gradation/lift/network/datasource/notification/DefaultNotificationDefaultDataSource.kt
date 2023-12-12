package com.gradation.lift.network.datasource.notification

import com.gradation.lift.model.model.notification.Notice
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.NotificationService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultNotificationDefaultDataSource @Inject constructor(
    private val notificationService: NotificationService,
    private val networkResultHandler: NetworkResultHandler,
) : NotificationDataSource {
    override suspend fun getNotice(): Flow<NetworkResult<List<Notice>>> =
        flow {
            networkResultHandler {
                notificationService.getNotice()
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }
}