package com.gradation.lift.feature.updateRoutine.profilePicture.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.picture.GetRoutineSetPictureUseCase
import com.gradation.lift.feature.updateRoutine.profilePicture.data.model.RoutineSetCategoryPicture
import com.gradation.lift.feature.updateRoutine.profilePicture.data.state.RoutineSetPictureUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [ProfilePictureViewModel]
 * @property routineSetPictureUiState 프로필 사진으로 설정할 사진 목록들에 대한 상태 목록을 성공적으로 불러올 시 [RoutineSetPictureUiState.Success] 반환
 * @since 2023-08-21 18:24:34
 */
@HiltViewModel
class ProfilePictureViewModel @Inject constructor(
    getRoutineSetPictureUseCase: GetRoutineSetPictureUseCase,
) : ViewModel() {


    val routineSetPictureUiState: StateFlow<RoutineSetPictureUiState> =
        getRoutineSetPictureUseCase().map { routineSetPictureList ->
            when (routineSetPictureList) {
                is DataState.Fail -> RoutineSetPictureUiState.Fail
                is DataState.Success -> RoutineSetPictureUiState.Success(
                    routineSetPictureList.data.groupBy { it.category }
                        .map { routineSetPictureGroup ->
                            RoutineSetCategoryPicture(
                                category = routineSetPictureGroup.key,
                                pictureList = routineSetPictureGroup.value.map { it.url }
                            )
                        }
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RoutineSetPictureUiState.Loading
        )

}


