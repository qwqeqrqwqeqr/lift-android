package com.gradation.lift.domain.usecase.inquiry

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.InquiryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateInquiryUseCase @Inject constructor(
    private val inquiryRepository: InquiryRepository,
) {
    operator fun invoke(
        content: String,
    ): Flow<DataState<Unit>> {
        return inquiryRepository.createInquiry(content)
    }
}