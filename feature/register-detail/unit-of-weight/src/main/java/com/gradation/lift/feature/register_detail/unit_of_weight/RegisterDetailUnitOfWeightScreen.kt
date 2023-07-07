package com.gradation.lift.feature.register_detail.unit_of_weight

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftToggleTextBox
import com.gradation.lift.designsystem.component.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.ui.DevicePreview

@Composable
fun RegisterDetailUnitOfWeightRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RegisterDetailUnitOfWeightViewModel = hiltViewModel(),
) {


    RegisterDetailUnitOfWeightScreen(
        modifier = modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RegisterDetailUnitOfWeightScreen(
    modifier: Modifier = Modifier,
) {
    Surface(
        color = LiftTheme.colorScheme.no5
    ) {
        Scaffold(
            topBar = {
                LiftTopBar(
                    title = "추가정보 입력",
                    onBackClick = { },
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
                        NumberCircle(number = it + 1, checked = it + 1 == 4)
                    }

                }
                Spacer(modifier = modifier.padding(28.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(color = LiftTheme.colorScheme.no4),
                        ) {
                            append("단위")
                        }
                        append("를 선택해주세요")
                    },
                    style = LiftTheme.typography.no1,
                    color = LiftTheme.colorScheme.no11,
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = LiftTheme.typography.no5.fontStyle,
                                fontFamily = LiftTheme.typography.no5.fontFamily,
                                fontWeight = LiftTheme.typography.no5.fontWeight,
                            ),
                        ) {
                            append("미터법 ")
                        }
                        append("미터, 킬로그램\n")
                        withStyle(
                            style = SpanStyle(
                                fontStyle = LiftTheme.typography.no5.fontStyle,
                                fontFamily = LiftTheme.typography.no5.fontFamily,
                                fontWeight = LiftTheme.typography.no5.fontWeight,
                            ),
                        ) {
                            append("야드법 ")
                        }
                        append("피트, 야드, 파운드")
                    },
                    style = LiftTheme.typography.no6,
                )
                Spacer(modifier = modifier.padding(15.dp))
                Row {
                    LiftToggleTextBox(
                        text = "미터법", checked = true, modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        onCheckedChange = {}
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    LiftToggleTextBox(
                        text = "야드법", checked = false, modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        onCheckedChange = {}
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


@DevicePreview
@Composable
fun RegisterDetailUnitOfWeightScreenPreview(
    modifier: Modifier = Modifier,
) {
    LiftMaterialTheme {
        RegisterDetailUnitOfWeightScreen(modifier)
    }
}