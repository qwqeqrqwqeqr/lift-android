package com.gradation.lift.domain.usecase.badge

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.BadgeRepository
import com.gradation.lift.model.model.badge.BadgeCondition
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserBadgeByConditionUseCase @Inject constructor(
    private val badgeRepository: BadgeRepository
) {
    operator fun invoke(): Flow<DataState<BadgeCondition>> =
        badgeRepository.getUserBadgeByCondition()
}