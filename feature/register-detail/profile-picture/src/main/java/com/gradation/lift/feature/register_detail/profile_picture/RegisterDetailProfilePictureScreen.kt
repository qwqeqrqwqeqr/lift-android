package com.gradation.lift.feature.register_detail.profile_picture

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.canvas.NumberCircle
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.register_detail.profile_picture.component.CompleteDialog


@Composable
fun RegisterDetailProfilePictureRoute(
    navController: NavController,
    navigateRegisterDetailProfilePictureToUnitOfWeight: () -> Unit,
    navigateRegisterDetailToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterDetailProfilePictureViewModel = hiltViewModel(),
) {

    val onVisibleDialog = viewModel.onVisibleDialog.collectAsStateWithLifecycle()
    RegisterDetailProfilePictureScreen(
        modifier = modifier,
        onVisibleDialog = onVisibleDialog,
        onClickCompleteDialogButton = navigateRegisterDetailToHome,
        onBackClickTopBar = navigateRegisterDetailProfilePictureToUnitOfWeight,
        onCompleteButtonClick = { viewModel.createUserDetail(navController) }
    )


    BackHandler(onBack = {
        navigateRegisterDetailProfilePictureToUnitOfWeight()
    })

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RegisterDetailProfilePictureScreen(
    modifier: Modifier = Modifier,
    onVisibleDialog: State<Boolean>,
    onClickCompleteDialogButton: () -> Unit,
    onBackClickTopBar: () -> Unit,
    onCompleteButtonClick: () -> Unit,
) {
    if (onVisibleDialog.value) {
        Surface(
            color = LiftTheme.colorScheme.no8,
            modifier = modifier.fillMaxSize()
        ) {
            CompleteDialog(onClickCompleteDialogButton = onClickCompleteDialogButton)
        }
    } else {
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
                        NumberCircle(number = it + 1, checked = it + 1 == 5)
                    }

                }
                Spacer(modifier = modifier.padding(28.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(color = LiftTheme.colorScheme.no4),
                        ) {
                            append("프로필 사진")
                        }
                        append("을 등록해주세요")
                    },
                    style = LiftTheme.typography.no1,
                    color = LiftTheme.colorScheme.no11,
                )

                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = onCompleteButtonClick,
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



