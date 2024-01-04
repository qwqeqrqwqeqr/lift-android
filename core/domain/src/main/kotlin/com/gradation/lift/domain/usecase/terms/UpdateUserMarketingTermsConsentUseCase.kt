package com.gradation.lift.domain.usecase.terms

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.TermsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserMarketingTermsConsentUseCase @Inject constructor(
    private val termsRepository: TermsRepository,
) {
    operator fun invoke(
        marketingConsent: Boolean,
    ): Flow<DataState<Boolean>> {
        return termsRepository.updateUserMarketingTermsConsent(marketingConsent)
    }
}