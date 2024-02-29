package com.gradation.lift.feature.home.home.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.component.topBar.LiftHomeTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.home.data.state.UserDetailUiState
import com.gradation.lift.ui.modifier.noRippleClickable


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HeaderView(
    modifier: Modifier = Modifier,
    userDetailUiState: UserDetailUiState,
    navigateHomeGraphToMyinfoProfileRouter: () -> Unit,
) {
    LiftHomeTopBar(
        modifier = modifier,
        actions = {
            when (userDetailUiState) {
                is UserDetailUiState.Fail -> {
                    Spacer(
                        modifier = modifier
                            .size(LiftTheme.space.space36)
                            .clip(CircleShape)
                            .background(LiftTheme.colorScheme.no8)
                    )
                }

                UserDetailUiState.Loading -> {
                    Spacer(
                        modifier = modifier
                            .size(LiftTheme.space.space36)
                            .clip(CircleShape)
                            .background(SkeletonBrush())

                    )
                }

                is UserDetailUiState.Success -> {
                    GlideImage(
                        model = userDetailUiState.userDetail.profilePicture,
                        contentDescription = "profilePicture",
                        modifier = modifier
                            .size(LiftTheme.space.space36)
                            .noRippleClickable(
                                onClick = navigateHomeGraphToMyinfoProfileRouter
                            )
                    )
                }
            }
        }
    )
}