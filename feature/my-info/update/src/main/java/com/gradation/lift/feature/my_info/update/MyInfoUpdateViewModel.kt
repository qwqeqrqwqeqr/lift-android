package com.gradation.lift.feature.my_info.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.common.utils.heightValidator
import com.gradation.lift.common.utils.nameValidator
import com.gradation.lift.common.utils.weightValidator
import com.gradation.lift.domain.usecase.checker.CheckerDuplicateNameUseCase
import com.gradation.lift.domain.usecase.user.GetUserDetailUseCase
import com.gradation.lift.domain.usecase.user.UpdateUserDetailUseCase
import com.gradation.lift.feature.my_info.update.data.state.UpdateUserDetailState
import com.gradation.lift.model.model.user.Gender
import com.gradation.lift.model.model.user.UserDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [MyInfoUpdateViewModel]
 * @property userDetail 프로필 사진을 업데이트 하기 위한 사용자의 아이디 등 사용자를 판별할 수 있는 정보
 * @property updateUserDetailState 프로필 업데이트 상태 (해당 상태가 [DataState.Success] 일경우 프로필이 업데이트 됨
 * @property nameText 사용자가 결정할 닉네임 텍스트
 * @property nameValidator 닉네임 유효성 검증
 * @property weightText 사용자의 몸무게 Float 타입을 사용할 경우 텍스트 필드에서 소수점을 처리할 때 문제가 있어 String 타입 사용
 * @property heightText 사용자의 키
 * 키,몸무게에서 Float 타입을 사용할 경우 텍스트 필드에서 소수점을 처리할 때 문제가 있어 String 타입 사용
 * @property weightValidator 몸무게 유효성 검증 (몸무게로 허용 되는 범위)
 * @property heightValidator 키 유효성 검증 (키로 허용 되는 범위)
 * @property gender 사용자의 성별
 * @since 2023-08-26 13:09:38
 */
@HiltViewModel
class MyInfoUpdateViewModel @Inject constructor(
    getUserDetailUseCase: GetUserDetailUseCase,
    private val checkerDuplicateNameUseCase: CheckerDuplicateNameUseCase,
    private val updateUserDetailUseCase: UpdateUserDetailUseCase
) : ViewModel() {

    var nameText: MutableStateFlow<String> = MutableStateFlow("")
    var nameValidator: StateFlow<Validator> =
        nameText.debounce(1000).distinctUntilChanged().flatMapLatest { name ->
            checkDuplicateName(name)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )

    var gender: MutableStateFlow<Gender> = MutableStateFlow(Gender.Male())


    var heightText: MutableStateFlow<String> = MutableStateFlow("")
    var weightText: MutableStateFlow<String> = MutableStateFlow("")

    var heightValidator: StateFlow<Validator> =
        heightText.mapLatest {
            it.toFloatOrNull()?.let { height ->
                if (heightValidator(height)) {
                    Validator(true, "")
                } else {
                    Validator(false, "정확한 정보를 입력해주세요.")
                }
            } ?: Validator(false, "")
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )
    var weightValidator: StateFlow<Validator> =
        weightText.mapLatest {
            it.toFloatOrNull()?.let { weight ->
                if (weightValidator(weight)) {
                    Validator(true, "")
                } else {
                    Validator(false, "정확한 정보를 입력해주세요.")
                }
            } ?: Validator(false, "")
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )


    private val userDetail: StateFlow<UserDetail> = getUserDetailUseCase().map {
        when (it) {
            is DataState.Fail -> {
                UserDetail()
            }

            is DataState.Success -> {
                it.data
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = UserDetail()
    )


    fun updateUserDetail(): () -> Unit = {
        viewModelScope.launch {
            updateUserDetailUseCase(
                userDetail = userDetail.value.copy(
                    name = nameText.value,
                    gender = gender.value,
                    height = heightText.value.toFloat(),
                    weight = weightText.value.toFloat(),
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

    internal val updateUserDetailState: MutableStateFlow<UpdateUserDetailState> =
        MutableStateFlow(UpdateUserDetailState.None)

    internal fun updateUpdateUserDetailState(): (UpdateUserDetailState) -> Unit = {
        updateUserDetailState.value = it
    }


    internal fun updateNameText(): (String) -> Unit = { it ->
        nameText.value = it
    }

    internal fun updateHeightText(): (String) -> Unit = { it ->
        heightText.value = it
    }

    internal fun updateWeightText(): (String) -> Unit = { it ->
        weightText.value = it
    }

    internal fun updateMale(): () -> Unit = {
        gender.value = Gender.Male()
    }

    internal fun updateFemale(): () -> Unit = {
        gender.value = Gender.Female()
    }


    private fun checkDuplicateName(name: String): Flow<Validator> {
        return checkerDuplicateNameUseCase(name).map {
            when (it) {
                is DataState.Fail -> Validator(false, "")
                is DataState.Success -> if (it.data) {
                    Validator(false, "이미 사용중인 닉네임이에요")
                } else {
                    if (nameValidator(name)) {
                        Validator(true, "")
                    } else {
                        if (name.isBlank()) {
                            Validator(false, "")
                        } else {
                            Validator(false, "2~5 자리의 한글만 사용 가능해요")
                        }
                    }
                }
            }
        }
    }

}