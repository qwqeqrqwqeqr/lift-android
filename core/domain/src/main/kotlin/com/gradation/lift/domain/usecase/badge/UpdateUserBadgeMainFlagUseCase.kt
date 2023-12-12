package com.gradation.lift.domain.usecase.badge

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.BadgeRepository
import com.gradation.lift.model.model.badge.UpdateUserBadgeMainFlag
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserBadgeMainFlagUseCase @Inject constructor(
    private val badgeRepository: BadgeRepository
) {
    operator fun invoke(updateUserBadgeMainFlag: UpdateUserBadgeMainFlag): Flow<DataState<Unit>> =
        badgeRepository.updateUserBadgeMainFlag(updateUserBadgeMainFlag)
}