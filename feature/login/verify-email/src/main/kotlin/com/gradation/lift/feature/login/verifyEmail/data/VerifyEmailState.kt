package com.gradation.lift.feature.login.verifyEmail.data

import com.gradation.lift.common.utils.Validator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalTime

/**
 * @property email 이메일
 * @property emailAuthenticationCode 이메일 인증 번호
 * @property selectedEmail 지정한 이메일 (인증번호를 입력하는 도웅에 이메일을 변경할 수 있기 때문에 인증번호를 보낸시점의 이메일을 일시적으로 저장한다.)
 * @property emailValidator 이메일 유효성 검증 모델
 * @property authenticationValidator 인증번호 유효성 검증 모델
 * @since 2024-01-06 18:44:09
 */
data class VerifyEmailState(
    val email: MutableStateFlow<String> = MutableStateFlow(""),
    val emailAuthenticationCode: MutableStateFlow<String> = MutableStateFlow(""),
    val selectedEmail: MutableStateFlow<String> = MutableStateFlow(""),
    val authenticationTimer: MutableStateFlow<LocalTime> = MutableStateFlow(LocalTime(0, 0, 0)),
    val emailValidator: MutableStateFlow<Validator> = MutableStateFlow(
        Validator(
            status = true,
            message = ""
        )
    ),
    val authenticationValidator: MutableStateFlow<Validator> = MutableStateFlow(
        Validator(
            status = true,
            message = ""
        )
    ),
    val viewModelScope: CoroutineScope,
) {
    val updateEmail: (String) -> Unit = { email.value = it }
    val updateEmailAuthenticationCode: (String) -> Unit = { emailAuthenticationCode.value = it }
    val updateSelectedEmail: (String) -> Unit = { selectedEmail.value = it }
    val updateEmailValidator: (Validator) -> Unit = { emailValidator.value = it }
    val updateAuthenticationValidator: (Validator) -> Unit = { authenticationValidator.value = it }

    private var timerJob: Job? =null




    val startTimer: () -> Unit = {
        authenticationTimer.value = LocalTime(0, 5, 0)
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (authenticationTimer.value.toSecondOfDay() > 0) {
                delay(DELAY)
                authenticationTimer.value =
                    LocalTime.fromSecondOfDay(authenticationTimer.value.toSecondOfDay() - 1)
            }
        }
        timerJob?.start()
    }


    val stopTimer: () -> Unit = {
        timerJob?.cancel()
    }

    companion object {
        const val DELAY = 1000L
    }

}
