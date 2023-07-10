package com.gradation.lift.domain.usecase.checker

import android.util.Log
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.CheckerRepository
import com.gradation.lift.domain.repository.WorkRepository
import com.gradation.lift.model.user.Name
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckerDuplicateNameUseCase @Inject constructor(
    private val checkerRepository: CheckerRepository,
) {
    operator fun invoke(name: Name): Flow<DataState<Boolean>> {
        return checkerRepository.checkDuplicateName(name = name)

    }
}