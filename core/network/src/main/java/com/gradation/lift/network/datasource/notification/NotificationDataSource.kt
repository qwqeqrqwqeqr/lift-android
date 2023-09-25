package com.gradation.lift.network.datasource.notification

import com.gradation.lift.model.model.notification.Notice
import com.gradation.lift.network.common.NetworkResult
import kotlinx.coroutines.flow.Flow

interface NotificationDataSource {


    suspend fun getNotice(): Flow<NetworkResult<List<Notice>>>
}