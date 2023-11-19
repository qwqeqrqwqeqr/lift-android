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
import com.gradation.lift.feature.my_info.update.data.state.UserDetailUiState
import com.gradation.lift.model.model.user.Gender
import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.ui.mapper.toText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [MyInfoUpdateViewModel]
 * @property userDetailUiState 상세정보 수정 Ui 상태
 * @property updateUserDetailState 프로필 업데이트 상태 (해당 상태가 [DataState.Success] 일경우 프로필이 업데이트 됨
 * @property updateCondition 업데이트 조건
 * @property nameText 사용자가 결정할 닉네임 텍스트
 * @property nameValidator 닉네임 유효성 검증
 * @property weightText 사용자의 몸무게 Float 타입을 사용할 경우 텍스트 필드에서 소수점을 처리할 때 문제가 있어 String 타입 사용
 * @property heightText 사용자의 키 Float 타입을 사용할 경우 텍스트 필드에서 소수점을 처리할 때 문제가 있어 String 타입 사용
 * @property weightValidator 몸무게 유효성 검증 (몸무게로 허용 되는 범위)
 * @property heightValidator 키 유효성 검증 (키로 허용 되는 범위)
 * @property gender 사용자의 성별
 * @property defaultName 기존 사용자의 이름, 값이 업데이트가 되었는지 판단할 때 사용
 * @property defaultHeight 기존 사용자의 키, 값이 업데이트가 되었는지 판단할 때 사용
 * @property defaultWeight 기존 사용자의 몸무게, 값이 업데이트가 되었는지 판단할 때 사용
 * @property defaultGender 기존 사용자의 성별, 값이 업데이트가 되었는지 판단할 때 사용
 * @property defaultProfilePicture 기존 사용자의 프로필 사진
 * [updateNameText] : 사용자가 작성한 이름을 업데이트 함 (text field 와 연결지어 사용)
 * [validateName] : 해당 이름을 사용해도 적절한지 판단해주는 메서드, 해당 메서드는 validator 반환함 (유효성 검증)
 * @since 2023-10-06 01:56:46
 */
@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class MyInfoUpdateViewModel @Inject constructor(
    getUserDetailUseCase: GetUserDetailUseCase,
    private val checkerDuplicateNameUseCase: CheckerDuplicateNameUseCase,
    private val updateUserDetailUseCase: UpdateUserDetailUseCase
) : ViewModel() {


    internal var nameText: MutableStateFlow<String> = MutableStateFlow("")
    internal var heightText: MutableStateFlow<String> = MutableStateFlow(0f.toString())
    internal var weightText: MutableStateFlow<String> = MutableStateFlow(0f.toString())
    internal var gender: MutableStateFlow<Gender> = MutableStateFlow(Gender.Male())

    private var defaultName: MutableStateFlow<String> = MutableStateFlow("")
    private var defaultHeight: MutableStateFlow<String> = MutableStateFlow(0f.toString())
    private var defaultWeight: MutableStateFlow<String> = MutableStateFlow(0f.toString())
    private var defaultGender: MutableStateFlow<Gender> = MutableStateFlow(Gender.Male())
    private var defaultProfilePicture: MutableStateFlow<String> = MutableStateFlow("")


    val userDetailUiState: StateFlow<UserDetailUiState> = getUserDetailUseCase().map {
        when (val state = it) {
            is DataState.Fail -> {
                UserDetailUiState.None
            }

            is DataState.Success -> {
                nameText.update { state.data.name }
                heightText.update { state.data.height.toText() }
                weightText.update { state.data.weight.toText() }
                gender.update { state.data.gender }

                defaultName.update { state.data.name }
                defaultHeight.update { state.data.height.toText() }
                defaultWeight.update { state.data.weight.toText() }
                defaultGender.update { state.data.gender }
                defaultProfilePicture.update { state.data.profilePicture }
                UserDetailUiState.Success
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = UserDetailUiState.None
    )


    internal var nameValidator: StateFlow<Validator> =
        nameText.debounce(1000).distinctUntilChanged().flatMapLatest { name ->
            validateName(name)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )
    internal var heightValidator: StateFlow<Validator> =
        heightText.mapLatest {
            it.toFloatOrNull()?.let { height ->
                if (heightValidator(height)) {
                    Validator(true, "")
                } else if (height == 0f) {
                    Validator(false, "")
                } else {
                    Validator(false, "정확한 정보를 입력해주세요.")
                }
            } ?: Validator(false, "")
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )
    internal var weightValidator: StateFlow<Validator> =
        weightText.mapLatest {
            it.toFloatOrNull()?.let { weight ->
                if (weightValidator(weight)) {
                    Validator(true, "")
                } else if (weight == 0f) {
                    Validator(false, "")
                } else {
                    Validator(false, "정확한 정보를 입력해주세요.")
                }
            } ?: Validator(false, "")
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )


    val updateCondition: StateFlow<Boolean> = combine(
        heightValidator,
        weightValidator,
        nameValidator,
        combine(combine(defaultName, nameText) { default, current ->
            default != current
        }, combine(defaultWeight, weightText) { default, current ->
            default != current
        }, combine(defaultHeight, heightText) { default, current ->
            default != current
        }, combine(defaultGender, gender) { default, current ->
            default != current
        }) { e1, e2, e3, e4 ->
            e1 || e2 || e3 || e4
        }
    ) { c1, c2, c3, c4 ->
        (c1.status && c2.status && c3.status && c4)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )

    fun updateUserDetail(): () -> Unit = {
        viewModelScope.launch {
            updateUserDetailUseCase(
                userDetail = UserDetail(
                    name = nameText.value,
                    gender = gender.value,
                    height = heightText.value.toFloat(),
                    weight = weightText.value.toFloat(),
                    profilePicture = defaultProfilePicture.value
                )
            ).collect {
                when (it) {
                    is DataState.Fail -> {
                        updateUserDetailState.value =
                            UpdateUserDetailState.Fail(message = "프로필 업데이트를 실패하였습니다.\n 잠시후 다시 시도해주세요.")
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

    internal fun updateHeightText(): (String) -> Unit = { it -> heightText.value = it }
    internal fun updateWeightText(): (String) -> Unit = { it -> weightText.value = it }
    internal fun updateMale(): () -> Unit = { gender.value = Gender.Male() }
    internal fun updateFemale(): () -> Unit = { gender.value = Gender.Female() }


    private fun validateName(name: String): Flow<Validator> {
        return defaultName.combine(checkerDuplicateNameUseCase(name)) { defaultName, checker ->
            when (checker) {
                is DataState.Fail -> Validator(false, "")
                is DataState.Success -> if (checker.data) {
                    if (defaultName == name) {
                        Validator(true, "")
                    } else {
                        Validator(false, "이미 사용중인 닉네임이에요")
                    }
                } else if (nameValidator(name)) {
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