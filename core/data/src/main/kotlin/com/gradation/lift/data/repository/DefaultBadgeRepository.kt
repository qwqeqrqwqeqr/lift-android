package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.BadgeRepository
import com.gradation.lift.model.model.badge.Badge
import com.gradation.lift.model.model.badge.BadgeCondition
import com.gradation.lift.model.model.badge.CreateUserBadge
import com.gradation.lift.model.model.badge.UpdateUserBadgeMainFlag
import com.gradation.lift.model.model.badge.UserBadge
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.badge.BadgeDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class DefaultBadgeRepository @Inject constructor(
    private val badgeDataSource: BadgeDataSource,
    private val dispatcherProvider: DispatcherProvider
) : BadgeRepository {
    override fun getBadge(): Flow<DataState<List<Badge>>> = flow {
        badgeDataSource.getBadge().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun getUserBadge(): Flow<DataState<List<UserBadge>>> = flow {
        badgeDataSource.getUserBadge().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun createUserBadge(createUserBadge: CreateUserBadge): Flow<DataState<Unit>> =
        flow {
            badgeDataSource.createUserBadge(createUserBadge).collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
        }.flowOn(dispatcherProvider.default)

    override fun getUserBadgeByMainFlag(): Flow<DataState<List<UserBadge>>> = flow {
        badgeDataSource.getUserBadgeByMainFlag().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun getUserBadgeByCondition(): Flow<DataState<BadgeCondition>> = flow {
        badgeDataSource.getUserBadgeByCondition().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun updateUserBadgeMainFlag(updateUserBadgeMainFlag: UpdateUserBadgeMainFlag): Flow<DataState<Unit>> = flow {
        badgeDataSource.updateUserBadgeMainFlag(updateUserBadgeMainFlag).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

}