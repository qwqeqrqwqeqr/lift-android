package com.gradation.lift.feature.login.terms_of_use

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.ToggleCheckbox
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.terms_of_use.component.CancelDialog
import com.gradation.lift.navigation.navigation.navigateSignUpProcessToSignIn
import com.gradation.lift.navigation.navigation.navigateToLoginComplete
import com.gradation.lift.ui.utils.DevicePreview

@Composable
fun LoginTermsOfUseRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginTermsOfUseViewModel = hiltViewModel(),
) {

    val signUpUiState: SignUpUiState by viewModel.signUpUiState.collectAsStateWithLifecycle()
    val allAcceptChecked = viewModel.allAcceptChecked.collectAsStateWithLifecycle()
    val termsOfUseChecked = viewModel.termsOfUseChecked.collectAsStateWithLifecycle()
    val personalInformationChecked =
        viewModel.personalInformationChecked.collectAsStateWithLifecycle()
    val locationTermsOfUseChecked =
        viewModel.locationTermsOfUseChecked.collectAsStateWithLifecycle()
    val marketingChecked = viewModel.marketingChecked.collectAsStateWithLifecycle()
    val navigateCondition = viewModel.navigateCondition.collectAsStateWithLifecycle()
    val onVisibleDialog = viewModel.onVisibleDialog.collectAsStateWithLifecycle()


    LoginTermsOfUseScreen(
        modifier = modifier,
        onBackClickTopBar = viewModel.updateOnVisibleDialog(true),
        allAcceptChecked = allAcceptChecked,
        termsOfUseChecked = termsOfUseChecked,
        personalInformationChecked = personalInformationChecked,
        locationTermsOfUseChecked = locationTermsOfUseChecked,
        marketingChecked = marketingChecked,
        onChangeAllAcceptChecked = viewModel.onChangeAllAcceptChecked(),
        onChangeTermsOfUseChecked = viewModel.onChangeTermsOfUseChecked(),
        onChangePersonalInformationChecked = viewModel.onChangePersonalInformationChecked(),
        onChangeLocationTermsOfUseChecked = viewModel.onChangeLocationTermsOfUseChecked(),
        onChangeMarketingChecked = viewModel.onChangeMarketingChecked(),
        onNextButtonClick = { viewModel.signUp(navController) },
        navigateCondition = navigateCondition,
        onVisibleDialog = onVisibleDialog,
        onClickDialogSuspendButton = { navController.navigateSignUpProcessToSignIn() },
        onClickDialogDismissButton = viewModel.updateOnVisibleDialog(false)
    )




    when (signUpUiState) {
        is SignUpUiState.Fail -> {
            Toast.makeText(
                LocalContext.current,
                "알 수 없는 오류로 회원가입을 진행할 수 없습니다. \n 다시 시도해주십시오.",
                Toast.LENGTH_SHORT
            ).show()
            navController.navigateSignUpProcessToSignIn()
        }
        SignUpUiState.Loading -> {}
        SignUpUiState.None -> {}
        SignUpUiState.Success -> {
            LaunchedEffect(true) {
                navController.navigateToLoginComplete()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoginTermsOfUseScreen(
    modifier: Modifier = Modifier,
    onBackClickTopBar: () -> Unit,
    allAcceptChecked: State<Boolean>,
    termsOfUseChecked: State<Boolean>,
    personalInformationChecked: State<Boolean>,
    locationTermsOfUseChecked: State<Boolean>,
    marketingChecked: State<Boolean>,
    onChangeAllAcceptChecked: (Boolean) -> Unit,
    onChangeTermsOfUseChecked: (Boolean) -> Unit,
    onChangePersonalInformationChecked: (Boolean) -> Unit,
    onChangeLocationTermsOfUseChecked: (Boolean) -> Unit,
    onChangeMarketingChecked: (Boolean) -> Unit,
    onNextButtonClick: () -> Unit,
    navigateCondition: State<Boolean>,
    onVisibleDialog: State<Boolean>,
    onClickDialogSuspendButton: () -> Unit,
    onClickDialogDismissButton: () -> Unit,
) {
    if (onVisibleDialog.value) {
        Surface(
            color = LiftTheme.colorScheme.no13,
            modifier = modifier.fillMaxSize()
        ) {
            CancelDialog(
                onClickDialogSuspendButton = onClickDialogSuspendButton,
                onClickDialogDismissButton = onClickDialogDismissButton
            )
        }
    } else {
        Surface(
            color = LiftTheme.colorScheme.no5
        ) {
            Scaffold(
                topBar = {
                    LiftBackTopBar(
                        title = "이용약관 동의",
                        onBackClickTopBar = onBackClickTopBar,
                    )
                },
            ) { padding ->
                Column(
                    modifier = modifier
                        .padding(padding)
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    Spacer(modifier = modifier.padding(18.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        ToggleCheckbox(
                            checked = allAcceptChecked.value,
                            onCheckedChange = onChangeAllAcceptChecked,
                            modifier = modifier.size(22.dp)
                        )
                        Spacer(modifier = modifier.padding(8.dp))
                        Text(
                            text = "아래약관에 모두 동의합니다.",
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no3
                        )
                    }
                    Spacer(modifier = modifier.padding(20.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        ToggleCheckbox(
                            checked = termsOfUseChecked.value,
                            onCheckedChange = onChangeTermsOfUseChecked,
                            modifier = modifier.size(22.dp)
                        )
                        Spacer(modifier = modifier.padding(8.dp))
                        Text(
                            text = "이용약관 필수 동의",
                            style = LiftTheme.typography.no5,
                            color = LiftTheme.colorScheme.no3

                        )
                    }
                    Spacer(modifier = modifier.padding(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        ToggleCheckbox(
                            checked = personalInformationChecked.value,
                            onCheckedChange = onChangePersonalInformationChecked,
                            modifier = modifier.size(22.dp)
                        )
                        Spacer(modifier = modifier.padding(8.dp))
                        Text(
                            text = "개인정보 처리방침 필수 동의",
                            style = LiftTheme.typography.no5,
                            color = LiftTheme.colorScheme.no3

                        )
                    }
                    Spacer(modifier = modifier.padding(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        ToggleCheckbox(
                            checked = locationTermsOfUseChecked.value,
                            onCheckedChange = onChangeLocationTermsOfUseChecked,
                            modifier = modifier.size(22.dp)
                        )
                        Spacer(modifier = modifier.padding(8.dp))
                        Text(
                            text = "위치정보 이용 약관 필수 동의",
                            style = LiftTheme.typography.no5,
                            color = LiftTheme.colorScheme.no3

                        )
                    }
                    Spacer(modifier = modifier.padding(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        ToggleCheckbox(
                            checked = marketingChecked.value,
                            onCheckedChange = onChangeMarketingChecked,
                            modifier = modifier.size(22.dp)
                        )
                        Spacer(modifier = modifier.padding(8.dp))
                        Text(
                            text = "[선택] 마케팅 정보 수신 선택 동의",
                            style = LiftTheme.typography.no5,
                            color = LiftTheme.colorScheme.no3
                        )
                    }
                    Spacer(modifier = modifier.padding(14.dp))

                    Text(
                        text = "‘선택' 항목에 동의하지 않아도 서비스 이용이 가능합니다. 개인정보 수집 및 이용에 대한 동의를 거부할 권리가 있으며, 동의 거부 시 회원제 서비스 이용이 제한됩니다.",
                        style = LiftTheme.typography.no7,
                        color = LiftTheme.colorScheme.no7,
                    )
                    Spacer(modifier = modifier.padding(18.dp))

                    LiftButton(
                        modifier = modifier.fillMaxWidth(),
                        onClick = onNextButtonClick,
                        enabled = navigateCondition.value
                    ) {
                        Text(
                            text = "회원가입",
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no5,
                        )
                    }

                }

            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@DevicePreview
@Composable
fun LoginTermsOfUseScreenPreview() {
    LiftMaterialTheme {
        LoginTermsOfUseScreen(
            modifier = Modifier,
            onBackClickTopBar = { },
            allAcceptChecked = mutableStateOf(false),
            termsOfUseChecked = mutableStateOf(false),
            personalInformationChecked = mutableStateOf(false),
            locationTermsOfUseChecked = mutableStateOf(false),
            marketingChecked = mutableStateOf(false),
            onChangeAllAcceptChecked = {},
            onChangeTermsOfUseChecked = {},
            onChangePersonalInformationChecked = {},
            onChangeLocationTermsOfUseChecked = {},
            onChangeMarketingChecked = {},
            onNextButtonClick = {},
            navigateCondition = mutableStateOf(false),
            onVisibleDialog = mutableStateOf(true),
            onClickDialogSuspendButton = {},
            onClickDialogDismissButton = {}
        )
    }
}