package com.gradation.lift.create_routine.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.create_routine.profile.data.model.RoutineSetCategoryPicture
import com.gradation.lift.create_routine.profile.data.model.SelectedPicture
import com.gradation.lift.create_routine.profile.data.state.RoutineSetPictureUiState
import com.gradation.lift.domain.usecase.picture.GetRoutineSetPictureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [CreateRoutineProfileViewModel]
 * @property selectedPicture 루틴세트 프로필로 선택한 사진
 * @property routineSetPictureUiState 프로필 사진으로 설정할 사진 목록들에 대한 상태 목록을 성공적으로 불러올 시 [RoutineSetPictureUiState.Success] 반환
 * @since 2023-08-21 18:24:34
 */
@HiltViewModel
class CreateRoutineProfileViewModel @Inject constructor(
    getRoutineSetPictureUseCase: GetRoutineSetPictureUseCase,
) : ViewModel() {

    val selectedPicture : MutableStateFlow<String> = MutableStateFlow("")

    val routineSetPictureUiState =
        combine(
            getRoutineSetPictureUseCase(),
            selectedPicture
        ) { routineSetPictureList, selectedPictureUrl ->
            when (routineSetPictureList) {
                is DataState.Fail -> RoutineSetPictureUiState.Fail
                is DataState.Success -> RoutineSetPictureUiState.Success(
                    routineSetPictureList.data.groupBy { it.category }
                        .map { routineSetPictureGroup ->
                            RoutineSetCategoryPicture(
                                category = routineSetPictureGroup.key,
                                picture = routineSetPictureGroup.value.map { routineSetPicture ->
                                    SelectedPicture(
                                        url = routineSetPicture.url,
                                        selected = (selectedPictureUrl == routineSetPicture.url)
                                    )
                                }
                            )
                        }
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RoutineSetPictureUiState.Loading
        )

    fun updateSelectedPicture(): (String) -> Unit = {
        selectedPicture.value = it
    }
}


