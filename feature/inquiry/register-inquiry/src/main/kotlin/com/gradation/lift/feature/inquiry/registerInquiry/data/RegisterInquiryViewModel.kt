package com.gradation.lift.feature.inquiry.registerInquiry.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.inquiry.CreateInquiryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterInquiryViewModel @Inject constructor(
    private val createInquiryUseCase: CreateInquiryUseCase,
) : ViewModel() {

    val content: MutableStateFlow<String> = MutableStateFlow("")
    val updateContent: (String) -> Unit = { content.value = it }


    val registerInquiryState: MutableStateFlow<RegisterInquiryState> =
        MutableStateFlow(RegisterInquiryState.None)
    val updateRegisterInquiryState: (RegisterInquiryState) -> Unit =
        { registerInquiryState.value = it }


    val createInquiry: () -> Unit = {
        viewModelScope.launch {
            createInquiryUseCase(content.value).collect {
                when (it) {
                    is DataState.Fail -> updateRegisterInquiryState(RegisterInquiryState.Fail)
                    is DataState.Success -> updateRegisterInquiryState(RegisterInquiryState.Success)
                }
            }
        }
    }


}