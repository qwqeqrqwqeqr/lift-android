package com.gradation.lift.feature.registerDetail.common.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.common.utils.decimalNumberValidator
import com.gradation.lift.common.utils.heightValidator
import com.gradation.lift.common.utils.nameValidator
import com.gradation.lift.common.utils.weightValidator
import com.gradation.lift.domain.usecase.auth.SignOutUseCase
import com.gradation.lift.domain.usecase.checker.CheckerDuplicateNameUseCase
import com.gradation.lift.domain.usecase.user.CreateUserDetailUseCase
import com.gradation.lift.model.model.user.Gender
import com.gradation.lift.model.model.user.UserDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [RegisterDetailSharedViewModel]
 * 사용자 상세정보 등록과정에서 발생하는 데이터들을 저장하는 뷰모델
 * @property currentRegisterProgressNumber 현재 진행 순번을 의미
 * @property totalRegisterProgressNumber 진행순서의 총 개수를 의미 (2023-08-17 기준 4개)
 *
 * @property name 사용자 닉네임
 * @property gender 사용자 성별
 * @property height 사용자 키
 * @property weight 사용자 몸무게
 * @property profilePicture 사용자 프로필 사진 주소
 * @property createUserDetailState 상세정보 등록 상태 값, 초기 값 : [CreateUserDetailState.None]
 * @since 2024-01-03 15:09:09
 */
@HiltViewModel
class RegisterDetailSharedViewModel @Inject constructor(
    private val createUserDetailUseCase: CreateUserDetailUseCase,
    private val checkerDuplicateNameUseCase: CheckerDuplicateNameUseCase,
    private val signOutUseCase: SignOutUseCase,
) : ViewModel() {

    val currentRegisterProgressNumber: MutableStateFlow<Int> = MutableStateFlow(1)
    val totalRegisterProgressNumber: StateFlow<Int> = MutableStateFlow(4).asStateFlow()

    val name: MutableStateFlow<String> = MutableStateFlow("")
    val gender: MutableStateFlow<Gender> = MutableStateFlow(Gender.Male())
    val height: MutableStateFlow<String> = MutableStateFlow("")
    val weight: MutableStateFlow<String> = MutableStateFlow("")
    val profilePicture: MutableStateFlow<String> = MutableStateFlow("")

    val createUserDetailState: MutableStateFlow<CreateUserDetailState> =
        MutableStateFlow(CreateUserDetailState.None)

    val cancelDialogView: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val completeDialogView: MutableStateFlow<Boolean> = MutableStateFlow(false)


    var nameValidator: StateFlow<Validator> =
        name.flatMapLatest { name ->
            if (name.isEmpty()) flowOf(Validator(true, ""))
            else if (!nameValidator(name)) flowOf(Validator(false, "2~8 자리의 한글만 사용 가능해요"))
            else checkDuplicateName(name)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator(true, "")
        )


    var heightValidator: StateFlow<Validator> =
        height.mapLatest { height ->
            if (height.isEmpty()) {
                Validator(true, "")
            } else {
                if (decimalNumberValidator(height)) {
                    if (heightValidator(height.toFloat()))
                        Validator(true, "")
                    else Validator(false, "정확한 정보를 입력해주세요.")
                } else {
                    Validator(false, "정확한 정보를 입력해주세요.")
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator(true, "")
        )
    var weightValidator: StateFlow<Validator> =
        weight.mapLatest { weight ->
            if (weight.isEmpty()) {
                Validator(true, "")
            } else {
                if (decimalNumberValidator(weight)) {
                    if (weightValidator(weight.toFloat()))
                        Validator(true, "")
                    else Validator(false, "정확한 정보를 입력해주세요.")
                } else {
                    Validator(false, "정확한 정보를 입력해주세요.")
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator(true, "")
        )


    private fun checkDuplicateName(name: String): Flow<Validator> {
        return checkerDuplicateNameUseCase(name).debounce(1000).distinctUntilChanged().map {
            when (it) {
                is DataState.Fail -> Validator(false, "")
                is DataState.Success ->
                    if (it.data) Validator(false, "이미 사용중인 닉네임이에요")
                    else Validator(true, "")
            }
        }
    }


    fun updateCurrentRegisterProgressNumber(): (Int) -> Unit =
        { currentRegisterProgressNumber.value = it }

    fun updateCancelDialogView(): (Boolean) -> Unit = { cancelDialogView.value = it }
    fun updateCompleteDialogView(): (Boolean) -> Unit = { completeDialogView.value = it }

    fun updateName(): (String) -> Unit = { name.value = it }
    fun updateGender(): (Gender) -> Unit = { gender.value = it }
    fun updateHeight(): (String) -> Unit = { height.value = it }
    fun updateWeight(): (String) -> Unit = { weight.value = it }
    fun updateProfilePicture(): (String) -> Unit =
        { profilePicture.value = it }

    fun updateCreateUserDetailState(): (CreateUserDetailState) -> Unit =
        { createUserDetailState.value = it }

    fun createUserDetail(): () -> Unit = {
        viewModelScope.launch {
            createUserDetailUseCase(
                UserDetail(
                    name = name.value,
                    gender = gender.value,
                    height = height.value.toFloat(),
                    weight = weight.value.toFloat(),
                    profilePicture = profilePicture.value,
                )
            ).collect { createUserDetailResult ->
                when (createUserDetailResult) {
                    is DataState.Success -> createUserDetailState.value =
                        CreateUserDetailState.Success

                    is DataState.Fail -> createUserDetailState.value =
                        CreateUserDetailState.Fail(createUserDetailResult.message)
                }
            }
        }
    }

    fun signOut(): () -> Unit = {
        viewModelScope.launch { signOutUseCase().collect() }
    }

}
