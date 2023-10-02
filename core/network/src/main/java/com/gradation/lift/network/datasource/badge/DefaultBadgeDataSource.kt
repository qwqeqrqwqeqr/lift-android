package com.gradation.lift.network.datasource.badge

import com.gradation.lift.model.model.badge.Badge
import com.gradation.lift.model.model.badge.BadgeCondition
import com.gradation.lift.model.model.badge.CreateUserBadge
import com.gradation.lift.model.model.badge.UpdateUserBadgeMainFlag
import com.gradation.lift.model.model.badge.UserBadge
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.dto.badge.CreateUserBadgeRequestDto
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.mapper.toDto
import com.gradation.lift.network.service.BadgeService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultBadgeDataSource @Inject constructor(
    private val badgeService: BadgeService,
    private val networkResultHandler: NetworkResultHandler,
) : BadgeDataSource {
    override suspend fun getBadge(): Flow<NetworkResult<List<Badge>>> =
        flow {
            networkResultHandler {
                badgeService.getBadge()
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }

    override suspend fun getUserBadge(): Flow<NetworkResult<List<UserBadge>>> =
        flow {
            networkResultHandler {
                badgeService.getUserBadge()
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }

    override suspend fun createUserBadge(createUserBadge: CreateUserBadge): Flow<NetworkResult<Unit>> =
        flow {
            networkResultHandler {
                badgeService.createUserBadge(
                    CreateUserBadgeRequestDto(createUserBadge.toDto())
                )
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(Unit))
                }
            }
        }

    override suspend fun getUserBadgeByMainFlag(): Flow<NetworkResult<List<UserBadge>>> =
        flow {
            networkResultHandler {
                badgeService.getUserBadgeByMainFlag()
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }

    override suspend fun getUserBadgeByCondition(): Flow<NetworkResult<BadgeCondition>> =
        flow {
            networkResultHandler {
                badgeService.getUserBadgeByCondition()
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }

    override suspend fun updateUserBadgeMainFlag(updateUserBadgeMainFlag: UpdateUserBadgeMainFlag): Flow<NetworkResult<Unit>> =
        flow {
            networkResultHandler {
                badgeService.updateUserBadgeMainFlag(updateUserBadgeMainFlag.toDto())
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(Unit))
                }
            }
        }


}