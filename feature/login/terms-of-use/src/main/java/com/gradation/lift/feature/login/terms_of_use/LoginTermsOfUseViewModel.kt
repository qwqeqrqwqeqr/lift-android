package com.gradation.lift.feature.login.terms_of_use

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.SignUpUseCase
import com.gradation.lift.model.auth.SignInInfo
import com.gradation.lift.model.auth.SignUpInfo
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.getStringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginTermsOfUseViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
) : ViewModel() {


    var allAcceptChecked by mutableStateOf(false)
    var termsOfUseChecked by mutableStateOf(false)
    var personalInformationChecked by mutableStateOf(false)
    var locationTermsOfUseChecked by mutableStateOf(false)
    var marketingChecked by mutableStateOf(false)


    var navigateCondition by mutableStateOf(false)
    var signUpUiState = MutableStateFlow<SignUpUiState>(SignUpUiState.None)

    fun onChangeAllAcceptChecked(): (Boolean) -> Unit = {
        allAcceptChecked = it
        if (allAcceptChecked) {
            termsOfUseChecked = true
            personalInformationChecked = true
            locationTermsOfUseChecked = true
            marketingChecked = true
        } else {
            termsOfUseChecked = false
            personalInformationChecked = false
            locationTermsOfUseChecked = false
            marketingChecked = false
        }
        updateNavigateCondition()
    }

    fun onChangeTermsOfUseChecked(): (Boolean) -> Unit = {
        termsOfUseChecked = it
        updateNavigateCondition()
    }

    fun onChangePersonalInformationChecked(): (Boolean) -> Unit = {
        personalInformationChecked = it
        updateNavigateCondition()
    }

    fun onChangeLocationTermsOfUseChecked(): (Boolean) -> Unit = {
        locationTermsOfUseChecked = it
        updateNavigateCondition()
    }

    fun onChangeMarketingChecked(): (Boolean) -> Unit = {
        marketingChecked = it
        updateNavigateCondition()
    }


    private fun updateNavigateCondition() {
        navigateCondition =
            (termsOfUseChecked && personalInformationChecked && locationTermsOfUseChecked)
    }


    fun signUp(navController: NavController) {
        viewModelScope.launch {
            signUpUiState.value = SignUpUiState.Loading
            signUpUseCase(
                SignUpInfo(
                    id = navController.getStringValue(SavedStateHandleKey.SignUpKey.EMAIL_KEY),
                    password = navController.getStringValue(SavedStateHandleKey.SignUpKey.PASSWORD_KEY)
                )
            ).map {
                when (it) {
                    is DataState.Fail -> {
                        Log.d("login", "${it.message} 실패")
                        SignUpUiState.Fail(it.message)
                    }
                    is DataState.Success -> {
                        Log.d("login", "${it.data} 성공")
                        SignUpUiState.Success
                    }
                }
            }.collect {
                signUpUiState.value = it
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
