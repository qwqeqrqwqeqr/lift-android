package com.gradation.lift.feature.login.terms_of_use

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftTopBar
import com.gradation.lift.designsystem.component.ToggleCheckbox
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.navigation.navigation.navigateSignUpProcessToSignIn
import com.gradation.lift.navigation.navigation.navigateToLoginComplete
import com.gradation.lift.ui.DevicePreview

@Composable
fun LoginTermsOfUseRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginTermsOfUseViewModel = hiltViewModel(),
) {

    val signUpUiState: SignUpUiState by viewModel.signUpUiState.collectAsStateWithLifecycle()

    LoginTermsOfUseScreen(
        modifier = modifier,
        onTopBarBackClick = { navController.navigateSignUpProcessToSignIn() },
        allAcceptChecked = viewModel.allAcceptChecked,
        termsOfUseChecked = viewModel.termsOfUseChecked,
        personalInformationChecked = viewModel.personalInformationChecked,
        locationTermsOfUseChecked = viewModel.locationTermsOfUseChecked,
        marketingChecked = viewModel.marketingChecked,
        onChangeAllAcceptChecked = viewModel.onChangeAllAcceptChecked(),
        onChangeTermsOfUseChecked = viewModel.onChangeTermsOfUseChecked(),
        onChangePersonalInformationChecked = viewModel.onChangePersonalInformationChecked(),
        onChangeLocationTermsOfUseChecked = viewModel.onChangeLocationTermsOfUseChecked(),
        onChangeMarketingChecked = viewModel.onChangeMarketingChecked(),
        onNextButtonClick = { viewModel.signUp(navController) },
        navigateCondition = viewModel.navigateCondition
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
        SignUpUiState.Loading -> {


        }
        SignUpUiState.None -> {

        }
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
    onTopBarBackClick: () -> Unit,
    allAcceptChecked: Boolean,
    termsOfUseChecked: Boolean,
    personalInformationChecked: Boolean,
    locationTermsOfUseChecked: Boolean,
    marketingChecked: Boolean,
    onChangeAllAcceptChecked: (Boolean) -> Unit,
    onChangeTermsOfUseChecked: (Boolean) -> Unit,
    onChangePersonalInformationChecked: (Boolean) -> Unit,
    onChangeLocationTermsOfUseChecked: (Boolean) -> Unit,
    onChangeMarketingChecked: (Boolean) -> Unit,
    onNextButtonClick: () -> Unit,
    navigateCondition: Boolean,
) {
    Surface(
        color = LiftTheme.colorScheme.no5
    ) {
        Scaffold(
            topBar = {
                LiftTopBar(
                    title = "이용약관 동의",
                    onBackClick = onTopBarBackClick,
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
                        checked = allAcceptChecked,
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
                        checked = termsOfUseChecked,
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
                        checked = personalInformationChecked,
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
                        checked = locationTermsOfUseChecked,
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
                        checked = marketingChecked,
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
                    enabled = navigateCondition
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


@DevicePreview
@Composable
fun LoginTermsOfUseScreenPreview() {
    LiftMaterialTheme {
        LoginTermsOfUseScreen(
            modifier = Modifier,
            onTopBarBackClick = { },
            allAcceptChecked = false,
            termsOfUseChecked = false,
            personalInformationChecked = false,
            locationTermsOfUseChecked = false,
            marketingChecked = false,
            onChangeAllAcceptChecked = {},
            onChangeTermsOfUseChecked = {},
            onChangePersonalInformationChecked = {},
            onChangeLocationTermsOfUseChecked = {},
            onChangeMarketingChecked = {},
            onNextButtonClick = {},
            navigateCondition = false,
        )
    }
}