package com.gradation.lift.domain.usecase.notification

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.NotificationRepository
import com.gradation.lift.model.model.notification.Notice
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoticeUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) {
    operator fun invoke(): Flow<DataState<List<Notice>>> =
        notificationRepository.getNotice()
}