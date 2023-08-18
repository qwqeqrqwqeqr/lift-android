package com.gradation.lift.feature.register_detail.name.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.user.CreateUserDetailUseCase
import com.gradation.lift.model.model.user.Gender
import com.gradation.lift.model.model.user.UserDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [RegisterDetailSharedViewModel]
 * 사용자 상세정보 등록과정에서 발생하는 데이터들을 저장하는 뷰모델
 * @property currentRegisterProgressNumber 현재 진행 순번을 의미
 * @property totalRegisterProgressNumber 진행순서의 총 개수를 의미 (2023-08-17 기준 4개)
 *
 * @property createUserDetailName 사용자 닉네임
 * @property createUserDetailGender 사용자 성별
 * @property createUserDetailHeight 사용자 키
 * @property createUserDetailWeight 사용자 몸무게
 * @property createUserDetailProfilePicture 사용자 프로필 사진 주소
 *
 * @property createUserDetailState 상세정보 등록 상태 값, 초기 값 : [CreateUserDetailState.None]
 * @since 2023-08-17 19:32:30
 */
@HiltViewModel
class RegisterDetailSharedViewModel @Inject constructor(
    private val createUserDetailUseCase: CreateUserDetailUseCase,
) : ViewModel() {

    val currentRegisterProgressNumber: MutableStateFlow<Int> = MutableStateFlow(1)
    val totalRegisterProgressNumber: MutableStateFlow<Int> = MutableStateFlow(4)

    val createUserDetailName: MutableStateFlow<String> = MutableStateFlow("")
    private val createUserDetailGender: MutableStateFlow<Gender> = MutableStateFlow(Gender.Male())
    private val createUserDetailHeight: MutableStateFlow<Float> = MutableStateFlow(0f)
    private val createUserDetailWeight: MutableStateFlow<Float> = MutableStateFlow(0f)
    private val createUserDetailProfilePicture: MutableStateFlow<String> = MutableStateFlow("")


    val createUserDetailState: MutableStateFlow<CreateUserDetailState> =
        MutableStateFlow(CreateUserDetailState.None)

    fun updateCurrentRegisterProgressNumber(): (Int) -> Unit = {
        currentRegisterProgressNumber.value = it
    }


    fun updateCreateUserDetailName(): (String) -> Unit = { createUserDetailName.value = it }
    fun updateCreateUserDetailGender(): (Gender) -> Unit = { createUserDetailGender.value = it }
    fun updateCreateUserDetailHeight(): (Float) -> Unit = { createUserDetailHeight.value = it }
    fun updateCreateUserDetailWeight(): (Float) -> Unit = { createUserDetailWeight.value = it }
    fun updateCreateUserDetailProfilePicture(): (String) -> Unit =
        { createUserDetailName.value = it }

    fun updateCreateUserDetailState(): (CreateUserDetailState) -> Unit =
        { createUserDetailState.value = it }

    fun createUserDetail(): () -> Unit = {
        viewModelScope.launch {
            createUserDetailUseCase(
                UserDetail(
                    name = createUserDetailName.value,
                    gender = createUserDetailGender.value,
                    height = createUserDetailHeight.value,
                    weight = createUserDetailWeight.value,
                    profilePicture = createUserDetailProfilePicture.value,
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
}
