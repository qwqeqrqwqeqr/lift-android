package com.gradation.lift.domain.usecase.checker

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.CheckerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * [CheckerDuplicateNameUseCase]
 * 중복된 값이 있을 경우 true 반환
 * @since 2023-08-28 22:03:55
 */
class CheckerDuplicateNameUseCase @Inject constructor(
    private val checkerRepository: CheckerRepository,
) {
    operator fun invoke(name: String): Flow<DataState<Boolean>> {
        return checkerRepository.checkDuplicateName(name = name)

    }
}