package com.gradation.lift.feature.login.termsOfUse.data.state

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberTermsOfUseScreenState(
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    context: Context = LocalContext.current,
): TermsOfUseScreenState =
    remember(snackbarHostState, context) {
        TermsOfUseScreenState(snackbarHostState, context)
    }


data class TermsOfUseScreenState(
    val snackbarHostState: SnackbarHostState,
    val context: Context,
) {
    var allConsent: Boolean by mutableStateOf(false)
    var termsOfUseConsent: Boolean by mutableStateOf(false)
    var privacyPolicyConsent: Boolean by mutableStateOf(false)
    var marketingConsent: Boolean by mutableStateOf(false)

    val updateAllConsent: (Boolean) -> Unit = {
        when (it) {
            true -> {
                allConsent = true
                termsOfUseConsent = true
                privacyPolicyConsent = true
                marketingConsent = true
            }

            false -> {
                allConsent = false
                termsOfUseConsent = false
                privacyPolicyConsent = false
                marketingConsent = false
            }
        }
    }

    val updateTermsOfUseConsent: (Boolean) -> Unit = {
        when (it) {
            true -> termsOfUseConsent = true
            false -> {
                termsOfUseConsent = false
                allConsent = false
            }
        }
    }

    val updatePrivacyPolicyConsent: (Boolean) -> Unit = {
        when (it) {
            true -> privacyPolicyConsent = true
            false -> {
                privacyPolicyConsent = false
                allConsent = false
            }
        }
    }

    val updateMarketingConsent: (Boolean) -> Unit = {
        when (it) {
            true -> marketingConsent = true
            false -> {
                marketingConsent = false
                allConsent = false
            }
        }
    }

}

