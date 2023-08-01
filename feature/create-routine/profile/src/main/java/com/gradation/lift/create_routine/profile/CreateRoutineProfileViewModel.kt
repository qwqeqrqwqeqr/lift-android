package com.gradation.lift.create_routine.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.picture.GetRoutineSetPictureUseCase
import com.gradation.lift.model.picture.RoutineSetPicture
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CreateRoutineProfileViewModel @Inject constructor(
    private val getRoutineSetPictureUseCase: GetRoutineSetPictureUseCase
) : ViewModel() {


    val routineSetPictureUiState = getRoutineSetPictureUseCase().map { result ->
        when (result) {
            is DataState.Fail -> RoutineSetPictureUiState.Fail
            is DataState.Success -> RoutineSetPictureUiState.Success(
                result.data.groupBy { it.category }.map { routineSetPictureGroup ->
                    RoutineSetCategoryPicture(
                        category = routineSetPictureGroup.key,
                        picture = routineSetPictureGroup.value.map { routineSetPicture ->
                            SelectedPicture(routineSetPicture.url)
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


}


data class RoutineSetCategoryPicture(
    val category: String,
    val picture: List<SelectedPicture>
)

data class SelectedPicture(
    val url :String,
    val selected : Boolean = false
)


sealed interface RoutineSetPictureUiState {
    data class Success(val routineSetPictureList: List<RoutineSetCategoryPicture>) :
        RoutineSetPictureUiState

    object Loading : RoutineSetPictureUiState
    object Fail : RoutineSetPictureUiState
}