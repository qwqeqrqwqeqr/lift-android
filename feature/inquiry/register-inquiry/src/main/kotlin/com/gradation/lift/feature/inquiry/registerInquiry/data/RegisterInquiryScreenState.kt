package com.gradation.lift.feature.inquiry.registerInquiry.data

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import com.gradation.lift.ui.provider.LocalAppScope
import com.gradation.lift.ui.provider.LocalInfoSnackbarHostState
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberRegisterInquiryScreenState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    localInfoSnackbarHostState: SnackbarHostState = LocalInfoSnackbarHostState.current,
    focusManager: FocusManager = LocalFocusManager.current,
    appScope: CoroutineScope = LocalAppScope.current,
): RegisterInquiryScreenState =
    remember(snackbarHostState, localInfoSnackbarHostState, focusManager, appScope) {
        RegisterInquiryScreenState(
            snackbarHostState,
            localInfoSnackbarHostState,
            focusManager,
            appScope
        )
    }

data class RegisterInquiryScreenState(
    val snackbarHostState: SnackbarHostState,
    val localInfoSnackbarHostState: SnackbarHostState,
    val focusManager: FocusManager,
    val appScope: CoroutineScope,
)

