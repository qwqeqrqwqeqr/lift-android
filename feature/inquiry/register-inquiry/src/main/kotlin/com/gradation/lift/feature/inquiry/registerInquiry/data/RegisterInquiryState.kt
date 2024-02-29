package com.gradation.lift.feature.inquiry.registerInquiry.data

sealed interface RegisterInquiryState {
    data object Success : RegisterInquiryState
    data object None : RegisterInquiryState
    data object Fail : RegisterInquiryState
}