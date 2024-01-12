package com.gradation.lift.feature.myInfo.myInfo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.component.button.LiftDefaultButton
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.myInfo.myInfo.data.state.MyInfoScreenState
import com.gradation.lift.feature.myInfo.myInfo.data.state.MyInfoUiState


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MyInfoScreen(
    modifier: Modifier = Modifier,
    navigateMyInfoGraphToNoticeGraph: () -> Unit,
    navigateMyInfoToProfileInMyInfoGraph: () -> Unit,
    myInfoUiState: MyInfoUiState,
    myInfoScreenState: MyInfoScreenState,
) {
    Scaffold(
        snackbarHost = {
            LiftSnackBar(
                modifier = modifier,
                snackbarHostState = myInfoScreenState.snackbarHostState
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .background(LiftTheme.colorScheme.no17)
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            when (myInfoUiState) {
                is MyInfoUiState.Fail -> {}
                MyInfoUiState.Loading -> {}
                is MyInfoUiState.Success -> {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .background(LiftTheme.colorScheme.no5)
                            .padding(
                                top = LiftTheme.space.space40,
                                start = LiftTheme.space.space20,
                                end = LiftTheme.space.space20,
                                bottom = LiftTheme.space.space16,
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = modifier,
                            horizontalArrangement = Arrangement.spacedBy(
                                LiftTheme.space.space12,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            GlideImage(
                                modifier = modifier.size(LiftTheme.space.space36),
                                model = myInfoUiState.myInfoState.profilePicture,
                                contentDescription = "profilePicture"
                            )
                            LiftText(
                                modifier = modifier,
                                textStyle = LiftTextStyle.No3,
                                text = myInfoUiState.myInfoState.name,
                                color = LiftTheme.colorScheme.no3,
                                textAlign = TextAlign.Start
                            )
                        }
                        LiftDefaultButton(
                            modifier = modifier,
                            width = LiftTheme.space.space80,
                            height = LiftTheme.space.space36,
                            shape = RoundedCornerShape(LiftTheme.space.space8),
                            text = "프로필 보기",
                            onClick = navigateMyInfoToProfileInMyInfoGraph
                        )
                    }
                }
            }
        }
    }
}


