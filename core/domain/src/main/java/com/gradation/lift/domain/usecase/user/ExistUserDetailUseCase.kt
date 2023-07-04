package com.gradation.lift.domain.usecase.user

import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.CheckerRepository
import com.gradation.lift.domain.repository.UserRepository
import com.gradation.lift.domain.repository.WorkRepository
import com.gradation.lift.model.user.Email
import com.gradation.lift.model.user.Name
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExistUserDetailUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Flow<DataState<Boolean>> =
        userRepository.existUserDetail()
}