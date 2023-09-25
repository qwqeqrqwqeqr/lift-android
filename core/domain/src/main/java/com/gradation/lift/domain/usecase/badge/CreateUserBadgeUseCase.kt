package com.gradation.lift.domain.usecase.badge

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.BadgeRepository
import com.gradation.lift.model.model.badge.CreateUserBadge
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserBadgeUseCase @Inject constructor(
    private val badgeRepository: BadgeRepository
) {
    operator fun invoke(createUserBadge: CreateUserBadge): Flow<DataState<Boolean>> =
        badgeRepository.createUserBadge(createUserBadge)
}