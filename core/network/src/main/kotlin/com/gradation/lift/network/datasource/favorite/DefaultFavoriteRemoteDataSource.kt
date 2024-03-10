package com.gradation.lift.network.datasource.favorite

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.dto.favorite.UpdateWorkCategoryFavoriteRequestDto
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.FavoriteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DefaultFavoriteRemoteDataSource @Inject constructor(
    private val favoriteService: FavoriteService,
    private val networkResultHandler: NetworkResultHandler,
    private val dispatcherProvider: DispatcherProvider,
) : FavoriteRemoteDataSource {

    override suspend fun getWorkCategoryFavorite(): Flow<NetworkResult<List<Int>>> =
        flow {
            networkResultHandler {
                favoriteService.getWorkCategoryFavorite()
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(message = result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.workCategoryIdList))
                }
            }
        }.flowOn(dispatcherProvider.default)

    override suspend fun updateWorkCategoryFavorite(workCategoryId: Int): Flow<NetworkResult<Unit>> =
        flow {
            networkResultHandler {
                favoriteService.updateWorkCategoryFavorite(
                    UpdateWorkCategoryFavoriteRequestDto(workCategoryId)
                )
            }.collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(message = result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(Unit))
                }
            }
        }.flowOn(dispatcherProvider.default)
}

