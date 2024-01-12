package com.gradation.lift.myInfo.updateProfilePicture.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.picture.GetUserProfilePictureUseCase
import com.gradation.lift.domain.usecase.user.GetUserDetailUseCase
import com.gradation.lift.domain.usecase.user.UpdateUserDetailUseCase
import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.myInfo.updateProfilePicture.data.state.UpdateUserDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [MyInfoUpdateProfileViewModel]
 * @property userDetail 프로필 사진을 업데이트 하기 위한 사용자의 아이디 등 사용자를 판별할 수 있는 정보
 * @property updateUserDetailState 프로필 업데이트 상태 (해당 상태가 [DataState.Success] 일경우 프로필이 업데이트 됨
 * @property selectedProfilePicture 현재 선택 된 프로필 사진
 * @property updateCondition 프로필을 업데이트하기 위한 조건 (선택된 프로필 사진이 존재해야만함)
 * @since 2023-08-25 16:57:07
 */
@HiltViewModel
class MyInfoUpdateProfileViewModel @Inject constructor(
    getUserProfilePictureUseCase: GetUserProfilePictureUseCase,
    getUserDetailUseCase: GetUserDetailUseCase,
    private val updateUserDetailUseCase: UpdateUserDetailUseCase
) : ViewModel() {


    private val userDetail: StateFlow<UserDetail> = getUserDetailUseCase().map {
        when (val state = it) {
            is DataState.Fail -> {
                UserDetail()
            }

            is DataState.Success -> {
                selectedProfilePicture.update { state.data.profilePicture }
                state.data
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = UserDetail()
    )


    internal val updateUserDetailState: MutableStateFlow<UpdateUserDetailState> =
        MutableStateFlow(UpdateUserDetailState.None)

    internal val selectedProfilePicture: MutableStateFlow<String> =
        MutableStateFlow(userDetail.value.profilePicture)


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


    internal val updateCondition: StateFlow<Boolean> =
        selectedProfilePicture.map { selectedProfilePicture.value.isNotEmpty() }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )


    fun updateUserProfilePicture(): () -> Unit = {
        viewModelScope.launch {
            updateUserDetailUseCase(
                userDetail = userDetail.value.copy(
                    profilePicture = selectedProfilePicture.value
                )
            ).collect {
                when (it) {
                    is DataState.Fail -> {
                        updateUserDetailState.value =
                            UpdateUserDetailState.Fail(message = "프로필 사진 업데이트를 실패하였습니다.\n 잠시후 다시 시도해주세요.")
                    }

                    is DataState.Success -> {
                        updateUserDetailState.value = UpdateUserDetailState.Success
                    }
                }
            }
        }

    }

    internal fun updateSelectedProfile(): (String) -> Unit = {
        selectedProfilePicture.value = it
    }

    internal fun updateUpdateUserDetailState(): (UpdateUserDetailState) -> Unit = {
        updateUserDetailState.value = it
    }
}