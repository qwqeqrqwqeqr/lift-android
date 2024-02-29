package com.gradation.lift.domain.usecase.favorite

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWorkCategoryFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
) {
    operator fun invoke(): Flow<DataState<List<Int>>> {
        return favoriteRepository.getWorkCategoryFavorite()

    }
}