package com.gradation.lift.feature.register_detail.unit_of_weight

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.canvas.NumberCircle
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftToggleTextBox
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.ui.utils.DevicePreview

@Composable
fun RegisterDetailUnitOfWeightRoute(
    navController: NavController,
    navigateRegisterDetailUnitOfWeightToHeightWeight: () -> Unit,
    navigateRegisterDetailUnitOfWeightToProfilePicture: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterDetailUnitOfWeightViewModel = hiltViewModel(),
) {
    RegisterDetailUnitOfWeightScreen(
        modifier = modifier,
        onBackClickTopBar = navigateRegisterDetailUnitOfWeightToHeightWeight,
        kgValue = viewModel.kg,
        lbValue = viewModel.lb,
        onUpdateKg = viewModel.updateKg(),
        onUpdateLb = viewModel.updateLb(),
        onNextButtonClick = {
            viewModel.updateKey(navController)
            navigateRegisterDetailUnitOfWeightToProfilePicture()
        }
    )


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RegisterDetailUnitOfWeightScreen(
    modifier: Modifier = Modifier,
    onBackClickTopBar: () -> Unit,
    kgValue: Boolean,
    lbValue: Boolean,
    onUpdateKg: (Boolean) -> Unit,
    onUpdateLb: (Boolean) -> Unit,
    onNextButtonClick: () -> Unit,
) {

    Surface(
        color = LiftTheme.colorScheme.no5
    ) {
        Scaffold(
            topBar = {
                LiftBackTopBar(
                    title = "추가정보 입력",
                    onBackClickTopBar = onBackClickTopBar
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
                    repeat(5) {
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
                        text = "미터법", checked = kgValue, modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        onCheckedChange = onUpdateKg
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    LiftToggleTextBox(
                        text = "야드법", checked = lbValue, modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        onCheckedChange = onUpdateLb
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
internal fun RegisterDetailUnitOfWeightScreenPreview(
    modifier: Modifier = Modifier,
) {
    LiftMaterialTheme {
        RegisterDetailUnitOfWeightScreen(
            modifier = modifier,
            onBackClickTopBar = { },
            kgValue = true,
            lbValue = false,
            onUpdateKg = {},
            onUpdateLb = {},
            onNextButtonClick = {},
        )
    }
}

