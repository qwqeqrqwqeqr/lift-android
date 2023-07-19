package com.gradation.lift.feature.login.terms_of_use

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.SignUpDefaultUseCase
import com.gradation.lift.model.auth.SignUpInfo
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.findValueInBackStackEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginTermsOfUseViewModel @Inject constructor(
    private val signUpDefaultUseCase: SignUpDefaultUseCase,
) : ViewModel() {


    var allAcceptChecked = MutableStateFlow(false)
    var termsOfUseChecked = MutableStateFlow(false)
    var personalInformationChecked = MutableStateFlow(false)
    var locationTermsOfUseChecked = MutableStateFlow(false)
    var marketingChecked = MutableStateFlow(false)

    var signUpUiState = MutableStateFlow<SignUpUiState>(SignUpUiState.None)


    val navigateCondition: StateFlow<Boolean> = combine(
        termsOfUseChecked,
        personalInformationChecked,
        locationTermsOfUseChecked
    ) { e1, e2, e3 ->
        (e1 && e2 && e3)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )



    var onVisibleDialog = MutableStateFlow(false)


    fun updateOnVisibleDialog(value : Boolean) : () -> Unit ={
        onVisibleDialog.value = value
    }




    fun onChangeAllAcceptChecked(): (Boolean) -> Unit = {
        allAcceptChecked.value = it
        if (allAcceptChecked.value) {
            termsOfUseChecked.value = true
            personalInformationChecked.value = true
            locationTermsOfUseChecked.value = true
            marketingChecked.value = true
        } else {
            termsOfUseChecked.value = false
            personalInformationChecked.value = false
            locationTermsOfUseChecked.value = false
            marketingChecked.value = false
        }
    }

    fun onChangeTermsOfUseChecked(): (Boolean) -> Unit = {
        termsOfUseChecked.value = it
    }

    fun onChangePersonalInformationChecked(): (Boolean) -> Unit = {
        personalInformationChecked.value = it
    }

    fun onChangeLocationTermsOfUseChecked(): (Boolean) -> Unit = {
        locationTermsOfUseChecked.value = it
    }

    fun onChangeMarketingChecked(): (Boolean) -> Unit = {
        marketingChecked.value = it
    }


    fun signUp(navController: NavController) {
        viewModelScope.launch {
            navController.findValueInBackStackEntry(
                listOf(
                    SavedStateHandleKey.SignUpKey.EMAIL_KEY,
                    SavedStateHandleKey.SignUpKey.PASSWORD_KEY
                )
            ).apply {
                signUpUiState.value = SignUpUiState.Loading
                signUpDefaultUseCase(
                    SignUpInfo(
                        id = this[SavedStateHandleKey.SignUpKey.EMAIL_KEY] ?: "",
                        password = this[SavedStateHandleKey.SignUpKey.PASSWORD_KEY] ?: ""
                    )
                ).map {
                    when (it) {
                        is DataState.Fail -> { SignUpUiState.Fail(it.message) }
                        is DataState.Success -> { SignUpUiState.Success }
                    }
                }.collect {
                    signUpUiState.value = it
                }

            }
        }
    }
}

sealed interface SignUpUiState {
    object Success : SignUpUiState
    data class Fail(val message: String) : SignUpUiState
    object Loading : SignUpUiState
    object None : SignUpUiState
}
