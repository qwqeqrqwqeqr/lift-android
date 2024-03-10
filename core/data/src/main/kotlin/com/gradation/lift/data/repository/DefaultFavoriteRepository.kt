package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.FavoriteRepository
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.favorite.FavoriteRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultFavoriteRepository @Inject constructor(
    private val favoriteRemoteDataSource: FavoriteRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider,
) : FavoriteRepository {
    override fun getWorkCategoryFavorite(): Flow<DataState<List<Int>>> = flow {
        favoriteRemoteDataSource.getWorkCategoryFavorite().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun updateWorkCategoryFavorite(workCategoryId: Int): Flow<DataState<Unit>> = flow {
        favoriteRemoteDataSource.updateWorkCategoryFavorite(workCategoryId).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> emit(DataState.Success(result.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

}
