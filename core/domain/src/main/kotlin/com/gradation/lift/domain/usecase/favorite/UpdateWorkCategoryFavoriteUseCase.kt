package com.gradation.lift.domain.usecase.favorite

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateWorkCategoryFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
) {
    operator fun invoke(workCategoryId: Int): Flow<DataState<Unit>> {
        return favoriteRepository.updateWorkCategoryFavorite(workCategoryId)

    }
}