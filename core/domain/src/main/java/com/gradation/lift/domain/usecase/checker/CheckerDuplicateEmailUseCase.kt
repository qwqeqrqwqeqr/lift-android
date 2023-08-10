package com.gradation.lift.domain.usecase.checker

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.CheckerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckDuplicateEmailUseCase @Inject constructor(
    private val checkerRepository: CheckerRepository
) {
    operator fun invoke(email: String): Flow<DataState<Boolean>> =
        checkerRepository.checkDuplicateEmail(email)
}