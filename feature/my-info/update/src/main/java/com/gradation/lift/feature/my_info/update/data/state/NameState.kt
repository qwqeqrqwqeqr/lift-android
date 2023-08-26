package com.gradation.lift.feature.my_info.update.data.state

import com.gradation.lift.common.model.DataState
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.common.utils.nameValidator
import com.gradation.lift.domain.usecase.checker.CheckerDuplicateNameUseCase
import com.gradation.lift.model.model.user.UserDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * [NameState]
 * 사용자 이름과 관련한 상태
 * @property nameText 사용자가 결정할 닉네임 텍스트
 * @property nameValidator 닉네임 유효성 검증
 * [updateNameText] : 사용자가 작성한 이름을 업데이트 함 (text field 와 연결지어 사용)
 * [validateName] : 해당 이름을 사용해도 적절한지 판단해주는 메서드, 해당 메서드는 validator 반환함 (유효성 검증)
 * @since 2023-08-26 13:27:05
 */
class NameState(
    private val userDetail: UserDetail,
    private val checkerDuplicateNameUseCase: CheckerDuplicateNameUseCase,
    private val viewModelScope : CoroutineScope
) {

    internal var nameText: MutableStateFlow<String> = MutableStateFlow(userDetail.name)
    internal var nameValidator: StateFlow<Validator> =
        nameText.debounce(1000).distinctUntilChanged().flatMapLatest { name ->
            validateName(name)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )


    internal fun updateNameText(): (String) -> Unit = { it ->
        nameText.value = it
    }

    private fun validateName(name: String): Flow<Validator> {
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