package com.gradation.lift.domain.usecase.checker

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.CheckerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * [CheckDuplicateEmailUseCase]
 * 중복된 값이 있을 경우 true 반환
 * @since 2023-08-28 22:03:27
 */
class CheckDuplicateEmailUseCase @Inject constructor(
    private val checkerRepository: CheckerRepository
) {
    operator fun invoke(email: String): Flow<DataState<Boolean>> =
        checkerRepository.checkDuplicateEmail(email)
}