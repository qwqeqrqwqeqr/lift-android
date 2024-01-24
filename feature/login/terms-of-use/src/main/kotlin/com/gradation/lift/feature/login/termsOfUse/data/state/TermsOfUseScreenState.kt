package com.gradation.lift.feature.login.termsOfUse.data.state

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberTermsOfUseScreenState(
    snackbarHostState: SnackbarHostState =  remember {SnackbarHostState()},
    context: Context = LocalContext.current,
    completeDialogView: MutableState<Boolean> = rememberSaveable { mutableStateOf (false) },
    allConsent: MutableState<Boolean> = rememberSaveable { mutableStateOf (false) },
    termsOfUseConsent: MutableState<Boolean> = rememberSaveable { mutableStateOf (false) },
    privacyPolicyConsent: MutableState<Boolean> = rememberSaveable { mutableStateOf (false) },
    marketingConsent: MutableState<Boolean> = rememberSaveable { mutableStateOf (false) },
): TermsOfUseScreenState {

    return remember(
        snackbarHostState,
        allConsent,
        termsOfUseConsent,
        privacyPolicyConsent,
        marketingConsent
    ) {
        TermsOfUseScreenState(
            snackbarHostState,
            context,
            completeDialogView,
            allConsent,
            termsOfUseConsent,
            privacyPolicyConsent,
            marketingConsent
        )
    }
}


@Stable
class TermsOfUseScreenState(
    val snackbarHostState: SnackbarHostState,
    val context: Context,
    val completeDialogView: MutableState<Boolean>,
    var allConsent: MutableState<Boolean>,
    var termsOfUseConsent: MutableState<Boolean>,
    var privacyPolicyConsent: MutableState<Boolean>,
    var marketingConsent: MutableState<Boolean>,
) {
    val updateCompleteDialogView: (Boolean) -> Unit = {completeDialogView.value=it}

    val updateAllConsent: (Boolean) -> Unit = {
        when (it) {
            true -> {
                allConsent.value = true
                termsOfUseConsent.value = true
                privacyPolicyConsent.value = true
                marketingConsent.value = true
            }

            false -> {
                allConsent.value = false
                termsOfUseConsent.value = false
                privacyPolicyConsent.value = false
                marketingConsent.value = false
            }
        }
    }

    val updateTermsOfUseConsent: (Boolean) -> Unit = {
        when (it) {
            true -> termsOfUseConsent.value = true
            false -> {
                termsOfUseConsent.value = false
                allConsent.value = false
            }
        }
    }

    val updatePrivacyPolicyConsent: (Boolean) -> Unit = {
        when (it) {
            true -> privacyPolicyConsent.value = true
            false -> {
                privacyPolicyConsent.value = false
                allConsent.value = false
            }
        }
    }

    val updateMarketingConsent: (Boolean) -> Unit = {
        when (it) {
            true -> marketingConsent.value = true
            false -> {
                marketingConsent.value = false
                allConsent.value = false
            }
        }
    }

}

