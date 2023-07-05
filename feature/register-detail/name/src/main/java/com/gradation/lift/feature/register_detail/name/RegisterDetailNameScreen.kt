package com.gradation.lift.feature.register_detail.name

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.canvas.NumberCircle
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftNotIconTopBar
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.component.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.navigation.navigation.navigateRegisterDetailToHome
import com.gradation.lift.navigation.navigation.navigateSignUpProcessToSignIn
import com.gradation.lift.navigation.navigation.navigateToRegisterDetailGender
import com.gradation.lift.ui.DevicePreview

@Composable
fun RegisterDetailNameRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RegisterDetailNameViewModel = hiltViewModel(),
) {

    navController.navigateToRegisterDetailGender()
    navController.navigateRegisterDetailToHome()
    RegisterDetailNameScreen(
        modifier = modifier,
        onTopBarBackClick = { navController.navigateSignUpProcessToSignIn() },

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RegisterDetailNameScreen(
    modifier: Modifier = Modifier,
    onTopBarBackClick: () -> Unit,
    nameText:String,
    updateNameText: (String) -> Unit,
    nameValidationSupportText: Validator,
    onNextButtonClick: () -> Unit,
    navigateCondition: Boolean,
    ) {
    Surface(
        color = LiftTheme.colorScheme.no5
    ) {
        Scaffold(
            topBar = {
                LiftTopBar(
                    title = "회원가입",
                    onBackClick = { },
                    actions = {
                        LiftNotIconTopBar(
                            title = "추가정보 입력",
                            actions = {
                                ClickableText(
                                    text = AnnotatedString("건너뛰기"),
                                    style = LiftTheme.typography.no7 +
                                            TextStyle(
                                                textDecoration = TextDecoration.Underline,
                                                color = LiftTheme.colorScheme.no9,
                                                textAlign = TextAlign.Center
                                            ),
                                    onClick = { {} },
                                )

                                Spacer(modifier = modifier.padding(8.dp))
                            }
                        )
                    }
                )
            },
        ) { padding ->
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .padding(padding)
                    .fillMaxSize()
            ) {
                val focusManager = LocalFocusManager.current
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)) {
                    repeat(4) {
                        NumberCircle(number = it + 1, checked = it + 1 == 1)
                    }
                }
                Spacer(modifier = modifier.padding(28.dp))
                Text(
                    text = buildAnnotatedString {
                        append("원활한 리프트 사용을 위해 \n")
                        withStyle(
                            style = SpanStyle(color = LiftTheme.colorScheme.no4),
                        ) {
                            append("추가정보")
                        }
                        append("를 등록할게요")
                    },
                    style = LiftTheme.typography.no1,
                    color = LiftTheme.colorScheme.no11,
                )
                Spacer(modifier = modifier.padding(15.dp))
                NameTextField(
                    modifier = modifier,
                    nameText = "",
                    updateNameText = {},
                    focusManager = focusManager,
                    nameValidationSupportText = Validator()
                )
                Spacer(modifier = modifier.padding(18.dp))
                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = { },
                ) {
                    Text(
                        text = "다음",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }
            }


        }

    }
}

@Composable
internal fun NameTextField(
    modifier: Modifier = Modifier,
    nameText: String,
    updateNameText: (String) -> Unit,
    focusManager: FocusManager,
    nameValidationSupportText: Validator,
) {
    Text(
        text = "닉네임",
        style = LiftTheme.typography.no3,
        color = LiftTheme.colorScheme.no3,
    )
    Spacer(modifier = modifier.padding(4.dp))
    LiftTextField(
        value = nameText,
        onValueChange = updateNameText,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = "닉네임을 입력해주세요",
                style = LiftTheme.typography.no6,
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            },
        )
    )
    if (!nameValidationSupportText.status) {
        Text(
            text = nameValidationSupportText.message,
            style = LiftTheme.typography.no7,
            color = LiftTheme.colorScheme.no12
        )
    }
}


@DevicePreview
@Composable
fun RegisterDetailNameScreenPreview(
    modifier: Modifier = Modifier,
) {
    LiftMaterialTheme {
        RegisterDetailNameScreen(modifier)
    }
}