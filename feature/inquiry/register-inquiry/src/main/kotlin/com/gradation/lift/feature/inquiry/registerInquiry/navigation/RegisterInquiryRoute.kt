package com.gradation.lift.feature.inquiry.registerInquiry.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.feature.inquiry.registerInquiry.ui.RegisterInquiryScreen


@Composable
fun RegisterInquiryRoute(
    modifier: Modifier = Modifier,
) {
    BackHandler(onBack = {})

    RegisterInquiryScreen(
        modifier,
    )
}