package com.gradation.lift.feature.login.terms_of_use

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftTopBar
import com.gradation.lift.designsystem.component.ToggleCheckbox
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.navigation.navigation.navigateSignUpProcessToSignIn
import com.gradation.lift.ui.DevicePreview

@Composable
fun LoginTermsOfUseRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginTermsOfUseViewModel = hiltViewModel(),
) {

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
        navigateCondition = viewModel.navigateCondition
    )
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
    navigateCondition: Boolean,
) {
    Surface(color = MaterialTheme.colorScheme.surface) {
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
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }

        }
    }
}


@DevicePreview
@Composable
fun LoginTermsOfUseScreenPreview() {
    LiftTheme {
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
            navigateCondition = false,
        )
    }
}