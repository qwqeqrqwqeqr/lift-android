package com.gradation.lift.feature.register_detail.gender

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.canvas.NumberCircle
import com.gradation.lift.designsystem.component.*
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.navigation.navigation.navigateRegisterDetailToHome
import com.gradation.lift.navigation.navigation.navigateToRegisterDetailHeightWeight
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle
import com.gradation.lift.ui.utils.DevicePreview

@Composable
fun RegisterDetailGenderRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RegisterDetailGenderViewModel = hiltViewModel(),
) {

    val name = navController.getValueSavedStateHandle<String>(SavedStateHandleKey.RegisterDetailKey.NAME_KEY) ?: ""

    RegisterDetailGenderScreen(
        modifier = modifier,
        onTopBarSkipButtonClick = { navController.navigateRegisterDetailToHome() },
        onBackClickTopBar = { navController.popBackStack() },
        nameText = name,
        maleValue = viewModel.male,
        femaleValue = viewModel.female,
        onUpdateMale = viewModel.updateMale(),
        onUpdateFemale = viewModel.updateFemale(),
        onNextButtonClick = {
            viewModel.updateKey(navController)
            navController.navigateToRegisterDetailHeightWeight()
        },
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RegisterDetailGenderScreen(
    modifier: Modifier = Modifier,
    onTopBarSkipButtonClick: (Int) -> Unit,
    onBackClickTopBar: () -> Unit,
    nameText: String,
    maleValue: Boolean,
    femaleValue: Boolean,
    onUpdateMale: (Boolean) -> Unit,
    onUpdateFemale: (Boolean) -> Unit,
    onNextButtonClick: () -> Unit,

    ) {
    Surface(
        color = LiftTheme.colorScheme.no5
    ) {
        Scaffold(
            topBar = {
                LiftBackTopBar(
                    title = "추가정보 입력",
                    onBackClickTopBar = onBackClickTopBar,
                    actions = {
                        ClickableText(
                            text = AnnotatedString("건너뛰기"),
                            style = LiftTheme.typography.no7 +
                                    TextStyle(
                                        textDecoration = TextDecoration.Underline,
                                        color = LiftTheme.colorScheme.no9,
                                        textAlign = TextAlign.Center
                                    ),
                            onClick = onTopBarSkipButtonClick,
                        )

                        Spacer(modifier = modifier.padding(8.dp))
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
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)) {
                    repeat(4) {
                        NumberCircle(number = it + 1, checked = it + 1 == 2)
                    }

                }
                Spacer(modifier = modifier.padding(28.dp))
                Text(
                    text = buildAnnotatedString {
                        append("${nameText}님의 ")
                        withStyle(
                            style = SpanStyle(color = LiftTheme.colorScheme.no4),
                        ) {
                            append("성별")
                        }
                        append("은 무엇인가요?")
                    },
                    style = LiftTheme.typography.no1,
                    color = LiftTheme.colorScheme.no11,
                )
                Spacer(modifier = modifier.padding(15.dp))
                Row {
                    LiftToggleTextBox(
                        text = "남자", checked = maleValue,
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        onCheckedChange = onUpdateMale
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    LiftToggleTextBox(
                        text = "여자", checked = femaleValue, modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        onCheckedChange = onUpdateFemale
                    )
                }
                Spacer(modifier = modifier.padding(18.dp))
                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = onNextButtonClick,
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


@SuppressLint("UnrememberedMutableState")
@DevicePreview
@Composable
fun RegisterDetailGenderScreenPreview(
    modifier: Modifier = Modifier,
) {
    LiftMaterialTheme {
        RegisterDetailGenderScreen(
            modifier = modifier,
            onTopBarSkipButtonClick = {},
            onBackClickTopBar = {},
            nameText = "리프트",
            maleValue = true,
            femaleValue = false,
            onUpdateMale = {},
            onUpdateFemale = {},
            onNextButtonClick = {}
        )
    }
}