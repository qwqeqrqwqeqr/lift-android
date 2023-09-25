package com.gradation.lift.domain.usecase.badge

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.BadgeRepository
import com.gradation.lift.model.model.badge.UserBadge
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserBadgeUseCase @Inject constructor(
    private val badgeRepository: BadgeRepository
) {
    operator fun invoke(): Flow<DataState<List<UserBadge>>> =
        badgeRepository.getUserBadge()
}