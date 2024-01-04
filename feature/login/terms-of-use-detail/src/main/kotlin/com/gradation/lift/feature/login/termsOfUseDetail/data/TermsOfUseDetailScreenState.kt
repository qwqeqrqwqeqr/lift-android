package com.gradation.lift.feature.login.termsOfUseDetail.data

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberTermsOfUseDetailScreenState(
    scrollState: ScrollState= rememberScrollState(),
): TermsOfUseDetailScreenState =
    remember(scrollState) {
        TermsOfUseDetailScreenState(scrollState)
    }


data class TermsOfUseDetailScreenState(
    val scrollState: ScrollState,
)

