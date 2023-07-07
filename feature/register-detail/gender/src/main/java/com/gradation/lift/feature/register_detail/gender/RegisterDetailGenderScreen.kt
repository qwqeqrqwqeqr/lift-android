package com.gradation.lift.feature.register_detail.gender

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
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
import com.gradation.lift.navigation.saved_state.getStringValue
import com.gradation.lift.ui.DevicePreview

@Composable
fun RegisterDetailGenderRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RegisterDetailGenderViewModel = hiltViewModel(),
) {


    RegisterDetailGenderScreen(
        modifier = modifier,
        onTopBarSkipButtonClick = {
            viewModel.updateKey(navController)
            navController.navigateToRegisterDetailHeightWeight()
        },
        onTopBarBackClick = { navController.popBackStack() },
        nameText = navController.getStringValue(SavedStateHandleKey.RegisterDetailKey.NAME_KEY),
        male = viewModel.male,
        female = viewModel.female,
        onUpdateMale = viewModel.updateMale(),
        onUpdateFemale = viewModel.updateFemale()
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RegisterDetailGenderScreen(
    modifier: Modifier = Modifier,
    onTopBarSkipButtonClick: (Int) -> Unit,
    onTopBarBackClick: () -> Unit,
    nameText: String,
    male: Boolean,
    female: Boolean,
    onUpdateMale: (Boolean) -> Unit,
    onUpdateFemale: (Boolean) -> Unit,
) {
    Surface(
        color = LiftTheme.colorScheme.no5
    ) {
        Scaffold(
            topBar = {
                LiftTopBar(
                    title = "추가정보 입력",
                    onBackClick = onTopBarBackClick,
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
                        text = "남자", checked = male,
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        onCheckedChange = onUpdateMale
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    LiftToggleTextBox(
                        text = "여자", checked = female, modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        onCheckedChange = onUpdateFemale
                    )
                }
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
            onTopBarBackClick = {},
            nameText = "리프트",
            male = true,
            female = false,
            onUpdateMale = {},
            onUpdateFemale = {}
        )
    }
}