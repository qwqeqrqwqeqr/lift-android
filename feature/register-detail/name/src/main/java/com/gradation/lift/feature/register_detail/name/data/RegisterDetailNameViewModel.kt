package com.gradation.lift.feature.register_detail.name.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.common.utils.nameValidator
import com.gradation.lift.domain.usecase.checker.CheckerDuplicateNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [RegisterDetailNameViewModel]
 * @property nameText 사용자가 결정할 닉네임 텍스트
 * @property nameValidator 닉네임 유효성 검증
 * @property navigateCondition 다음 페이지로 넘어갈 수 있는 조건, false 경우 다음으로 넘어갈 수 없게 설정
 * @since 2023-08-17 19:08:31
 */
@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class RegisterDetailNameViewModel @Inject constructor(
    private val checkerDuplicateNameUseCase: CheckerDuplicateNameUseCase,
) : ViewModel() {

    var nameText = MutableStateFlow("")
    var nameValidator: StateFlow<Validator> =
        nameText.debounce(1000).distinctUntilChanged().flatMapLatest { name ->
            checkDuplicateName(name)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )

    var navigateCondition: StateFlow<Boolean> = nameValidator.map { it.status }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )

    internal fun updateName(): (String) -> Unit = { it ->
        nameText.value = it
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



