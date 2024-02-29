package com.gradation.lift.domain.usecase.notice

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.NoticeRepository
import com.gradation.lift.model.model.notice.Notice
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoticeByIdUseCase @Inject constructor(
    private val noticeRepository: NoticeRepository,
) {
    operator fun invoke(noticeId: Int): Flow<DataState<Notice>> =
        noticeRepository.getNoticeById(noticeId)
}