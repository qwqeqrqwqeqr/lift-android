package com.gradation.lift.feature.register_detail.profile_picture.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.picture.GetUserProfilePictureUseCase
import com.gradation.lift.model.model.picture.UserProfilePicture
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [ProfilePictureViewModel]
 * @property profilePictureList 사용자가 선택할 수 있는 프로필 사진 목록
 * @since  2024-01-03 19:22:08
 */
@HiltViewModel
class ProfilePictureViewModel @Inject constructor(
    getUserProfilePictureUseCase: GetUserProfilePictureUseCase,
) : ViewModel() {


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

}
