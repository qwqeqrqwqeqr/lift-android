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
 * [RegisterDetailProfilePictureViewModel]
 * @property profilePictureList 사용자가 선택할 수 있는 프로필 사진 목록
 * @property selectedProfilePicture  현재 선택된 프로필 사진
 * @property navigationCondition 다음 화면으로 넘어갈 수 있는 조건
 * @property onVisibleCompleteDialog 완료 팝업이 나오는 조건 (상세정보 등록 완료 시 해당 팝업 등장)
 * @since  2023-08-18 13:44:39¬
 */
@HiltViewModel
class RegisterDetailProfilePictureViewModel @Inject constructor(
    getUserProfilePictureUseCase: GetUserProfilePictureUseCase,
) : ViewModel() {


    internal val selectedProfilePicture: MutableStateFlow<String> = MutableStateFlow("")
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


    internal val navigationCondition: StateFlow<Boolean> =
        selectedProfilePicture.map { selectedProfilePicture.value.isNotEmpty() }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )
    internal var onVisibleCompleteDialog: MutableStateFlow<Boolean> = MutableStateFlow(false)


    internal fun updateSelectedProfile(): (String) -> Unit = {
        selectedProfilePicture.value = it
    }

    internal fun updateOnVisibleCompleteDialog(): (Boolean) -> Unit = {
        onVisibleCompleteDialog.value = it
    }



}
