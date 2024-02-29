package com.gradation.lift.feature.inquiry.registerInquiry.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.inquiry.registerInquiry.data.RegisterInquiryScreenState
import com.gradation.lift.feature.inquiry.registerInquiry.data.RegisterInquiryState
import com.gradation.lift.feature.inquiry.registerInquiry.data.RegisterInquiryViewModel
import com.gradation.lift.feature.inquiry.registerInquiry.data.rememberRegisterInquiryScreenState
import com.gradation.lift.feature.inquiry.registerInquiry.ui.RegisterInquiryScreen
import com.gradation.lift.ui.extensions.showImmediatelySnackbar
import kotlinx.coroutines.launch


@Composable
fun RegisterInquiryRoute(
    modifier: Modifier = Modifier,
    navigatePreGraph: () -> Unit,
    viewModel: RegisterInquiryViewModel = hiltViewModel(),
    registerInquiryScreenState: RegisterInquiryScreenState = rememberRegisterInquiryScreenState(),
) {

    val registerInquiryState: RegisterInquiryState by viewModel.registerInquiryState.collectAsStateWithLifecycle()
    val content: String by viewModel.content.collectAsStateWithLifecycle()

    val updateRegisterInquiryState: (RegisterInquiryState) -> Unit =
        viewModel.updateRegisterInquiryState
    val updateContent: (String) -> Unit = viewModel.updateContent

    val createInquiry: () -> Unit = viewModel.createInquiry


    LaunchedEffect(registerInquiryState) {
        when (registerInquiryState) {
            RegisterInquiryState.Fail -> {
                registerInquiryScreenState.snackbarHostState.showImmediatelySnackbar("의견 전달을 실패하였습니다.\n잠시후에 다시 시도해주세요")
                updateRegisterInquiryState(RegisterInquiryState.None)
            }

            RegisterInquiryState.None -> {}
            RegisterInquiryState.Success -> {
                registerInquiryScreenState.appScope.launch {
                    registerInquiryScreenState.localInfoSnackbarHostState.showImmediatelySnackbar("성공적으로 의견을 전달하였습니다.")
                }
                navigatePreGraph()
            }
        }

    }

    BackHandler(onBack = navigatePreGraph)

    RegisterInquiryScreen(
        modifier,
        content,
        updateContent,
        navigatePreGraph,
        createInquiry,
        registerInquiryScreenState
    )
}