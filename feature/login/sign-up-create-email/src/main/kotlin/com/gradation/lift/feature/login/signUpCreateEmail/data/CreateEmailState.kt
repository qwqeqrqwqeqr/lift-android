package com.gradation.lift.feature.login.signUpCreateEmail.data

import com.gradation.lift.common.utils.Validator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalTime

data class CreateEmailState(
    val email: MutableStateFlow<String> = MutableStateFlow(""),
    val emailAuthenticationCode: MutableStateFlow<String> = MutableStateFlow(""),
    val selectedEmail: MutableStateFlow<String> = MutableStateFlow(""),
    val authenticationTimer: MutableStateFlow<LocalTime> = MutableStateFlow(LocalTime(0, 5, 0)),
    val emailValidator: MutableStateFlow<Validator> = MutableStateFlow(Validator(status = true, message = "")),
    val authenticationValidator: MutableStateFlow<Validator> = MutableStateFlow(Validator(status = true, message = "")),
    val viewModelScope: CoroutineScope,
) {
    val updateEmail: (String) -> Unit = { email.value = it }
    val updateEmailAuthenticationCode: (String) -> Unit = { emailAuthenticationCode.value = it }
    val updateSelectedEmail: (String) -> Unit = { selectedEmail.value = it }
    val updateEmailValidator: (Validator) -> Unit = { emailValidator.value = it }
    val updateAuthenticationValidator: (Validator) -> Unit = { authenticationValidator.value = it }

    private val timerJob = viewModelScope.launch {
        while (authenticationTimer.value.toSecondOfDay() > 0) {
            delay(1000L)
            authenticationTimer.value =
                LocalTime.fromSecondOfDay(authenticationTimer.value.toSecondOfDay() - 1)
        }
    }



    val startTimer: () -> Unit = {
        authenticationTimer.value = LocalTime(0, 5, 0)
        timerJob.start()
    }


    val stopTimer: () -> Unit = {
        timerJob.cancel()
    }


}
