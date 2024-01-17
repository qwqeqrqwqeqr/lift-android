package com.gradation.lift.myInfo.updateProfilePicture.data

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.picture.GetUserProfilePictureUseCase
import com.gradation.lift.domain.usecase.user.UpdateUserDetailProfilePictureUseCase
import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.model.model.user.UserDetailProfilePicture
import com.gradation.lift.myInfo.updateProfilePicture.data.state.UpdateProfilePictureState
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.MyInfo.USER_PROFILE_PICTURE_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [UpdateProfilePictureViewModel]
 * @property updateProfilePictureState 프로필 사진 업데이트 상태 (해당 상태가 [DataState.Success] 일경우 프로필이 업데이트 됨
 * @property selectedProfilePicture 현재 선택 된 프로필 사진
 * @since 2024-01-14 19:55:59
 */
@HiltViewModel
class UpdateProfilePictureViewModel @Inject constructor(
    getUserProfilePictureUseCase: GetUserProfilePictureUseCase,
    private val updateUserDetailProfilePictureUseCase: UpdateUserDetailProfilePictureUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val selectedProfilePicture: MutableStateFlow<String> = MutableStateFlow(
        Uri.decode(savedStateHandle.get<String>(USER_PROFILE_PICTURE_KEY))
    )
    internal val updateSelectedProfilePicture: (String) -> Unit =
        { selectedProfilePicture.value = it }


    internal val updateProfilePictureState: MutableStateFlow<UpdateProfilePictureState> =
        MutableStateFlow(UpdateProfilePictureState.None)
    internal val updateUpdateProfilePictureState: (UpdateProfilePictureState) -> Unit = {
        updateProfilePictureState.value = it
    }


    internal val profilePictureList: StateFlow<List<UserProfilePicture>> =
        getUserProfilePictureUseCase().map {
            when (it) {
                is DataState.Fail -> emptyList()
                is DataState.Success -> it.data
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    internal val updateUserProfilePicture: () -> Unit = {
        viewModelScope.launch {
            updateUserDetailProfilePictureUseCase(
                UserDetailProfilePicture(
                    profilePicture = selectedProfilePicture.value
                )
            ).collect {
                when (it) {
                    is DataState.Fail -> {
                        updateProfilePictureState.value =
                            UpdateProfilePictureState.Fail(message = "프로필 사진 업데이트를 실패하였습니다.\n 잠시후 다시 시도해주세요.")
                    }

                    is DataState.Success -> {
                        updateProfilePictureState.value = UpdateProfilePictureState.Success
                    }
                }
            }
        }

    }


}