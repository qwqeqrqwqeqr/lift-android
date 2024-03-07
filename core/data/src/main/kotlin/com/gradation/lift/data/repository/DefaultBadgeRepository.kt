package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.utils.ErrorMessage.CACHE_ERROR_MESSAGE
import com.gradation.lift.database.datasource.badge.BadgeLocalDataSource
import com.gradation.lift.database.datasource.userBadge.UserBadgeLocalDataSource
import com.gradation.lift.domain.repository.BadgeRepository
import com.gradation.lift.model.model.badge.Badge
import com.gradation.lift.model.model.badge.BadgeCondition
import com.gradation.lift.model.model.badge.CreateUserBadge
import com.gradation.lift.model.model.badge.UpdateUserBadgeMainFlag
import com.gradation.lift.model.model.badge.UserBadge
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.badge.BadgeRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DefaultBadgeRepository @Inject constructor(
    private val badgeRemoteDataSource: BadgeRemoteDataSource,
    private val badgeLocalDataSource: BadgeLocalDataSource,
    private val userBadgeLocalDataSource: UserBadgeLocalDataSource,
    private val dispatcherProvider: DispatcherProvider,
) : BadgeRepository {

    override fun getBadge(): Flow<DataState<List<Badge>>> = flow {
        badgeRemoteDataSource.getBadge().collect { result ->
            when (result) {
                is NetworkResult.Fail -> {
                    try {
                        badgeLocalDataSource.getAllBadge().collect {
                            if (it.isEmpty()) emit(DataState.Fail(result.message))
                            else emit(DataState.Success(it))
                        }
                    } catch (error: Throwable) {
                        emit(DataState.Fail(CACHE_ERROR_MESSAGE))
                    }

                }

                is NetworkResult.Success -> {
                    try {
                        badgeLocalDataSource.fetch(result.data)
                        emit(DataState.Success(result.data))
                    } catch (error: Throwable) {
                        emit(DataState.Fail(CACHE_ERROR_MESSAGE))
                    }
                }
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun getUserBadge(): Flow<DataState<List<UserBadge>>> = flow {
        badgeRemoteDataSource.getUserBadge().collect { result ->
            when (result) {
                is NetworkResult.Fail -> {
                    try {
                        userBadgeLocalDataSource.getAllUserBadge().collect {
                            if (it.isEmpty()) emit(DataState.Fail(result.message))
                            else emit(DataState.Success(it))
                        }
                    } catch (error: Throwable) {
                        emit(DataState.Fail(CACHE_ERROR_MESSAGE))
                    }
                }

                is NetworkResult.Success -> {
                    try {
                        userBadgeLocalDataSource.fetch(result.data)
                        emit(DataState.Success(result.data))
                    } catch (error: Throwable) {
                        emit(DataState.Fail(CACHE_ERROR_MESSAGE))
                    }
                }
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun createUserBadge(createUserBadge: CreateUserBadge): Flow<DataState<Unit>> =
        flow {
            badgeRemoteDataSource.createUserBadge(createUserBadge).collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(DataState.Success(result.data))
                }
            }
        }.flowOn(dispatcherProvider.default)

    override fun getUserBadgeByMainFlag(): Flow<DataState<List<UserBadge>>> = flow {
        badgeRemoteDataSource.getUserBadgeByMainFlag().collect { result ->
            when (result) {
                is NetworkResult.Fail -> {
                    try {
                        userBadgeLocalDataSource.getAllUserBadge().collect {
                            emit(DataState.Success(it.filter { it.mainFlag }))
                        }
                    } catch (error: Throwable) {
                        emit(DataState.Fail(CACHE_ERROR_MESSAGE))
                    }
                }

                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun getUserBadgeByCondition(): Flow<DataState<BadgeCondition>> = flow {
        badgeRemoteDataSource.getUserBadgeByCondition().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun updateUserBadgeMainFlag(updateUserBadgeMainFlag: UpdateUserBadgeMainFlag): Flow<DataState<Unit>> =
        flow {
            badgeRemoteDataSource.updateUserBadgeMainFlag(updateUserBadgeMainFlag)
                .collect { result ->
                    when (result) {
                        is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                        is NetworkResult.Success -> {
                            try {
                                userBadgeLocalDataSource.updateUserBadgeMainFlag(
                                    updateUserBadgeMainFlag
                                )
                                emit(DataState.Success(result.data))
                            } catch (error: Throwable) {
                                emit(DataState.Fail(CACHE_ERROR_MESSAGE))
                            }
                        }
                    }
                }
        }.flowOn(dispatcherProvider.default)

}