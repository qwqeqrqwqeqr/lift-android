package com.gradation.lift.feature.login.terms_of_use

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginTermsOfUseViewModel @Inject constructor(
) : ViewModel() {


    var allAcceptChecked by mutableStateOf(false)
    var termsOfUseChecked by mutableStateOf(false)
    var personalInformationChecked by mutableStateOf(false)
    var locationTermsOfUseChecked by mutableStateOf(false)
    var marketingChecked by mutableStateOf(false)


    var navigateCondition by mutableStateOf(false)

    fun onChangeAllAcceptChecked() : (Boolean) -> Unit = {
        allAcceptChecked = it
        if(allAcceptChecked){
            termsOfUseChecked=true
            personalInformationChecked=true
            locationTermsOfUseChecked=true
            marketingChecked=true
        } else{
            termsOfUseChecked=false
            personalInformationChecked=false
            locationTermsOfUseChecked=false
            marketingChecked=false
        }
        updateNavigateCondition()
    }

    fun onChangeTermsOfUseChecked(): (Boolean) -> Unit = {
        termsOfUseChecked = it
        updateNavigateCondition()
    }

    fun onChangePersonalInformationChecked() : (Boolean) -> Unit = {
        personalInformationChecked = it
        updateNavigateCondition()
    }

    fun onChangeLocationTermsOfUseChecked(): (Boolean) -> Unit = {
        locationTermsOfUseChecked = it
        updateNavigateCondition()
    }

    fun onChangeMarketingChecked() : (Boolean) -> Unit = {
        marketingChecked = it
        updateNavigateCondition()
    }


    private fun updateNavigateCondition() {
        navigateCondition =
            (allAcceptChecked && termsOfUseChecked && personalInformationChecked && locationTermsOfUseChecked)
    }


}
