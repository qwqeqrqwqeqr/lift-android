package com.gradation.lift.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.component.LiftHomeTopBar
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.data.state.UserDetailUiState


@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    userDetailUiState: UserDetailUiState,
    navigateHomeGraphToNotificationGraph: () -> Unit
) {
    LiftHomeTopBar(
        modifier = modifier,
        actions = {
            IconButton(onClick = navigateHomeGraphToNotificationGraph) {
                Icon(
                    painter = painterResource(LiftIcon.Bell),
                    contentDescription = "",
                    tint = Color.Unspecified,
                )
            }
            Spacer(modifier = modifier.padding(8.dp))
            when (userDetailUiState) {
                is UserDetailUiState.Fail -> {
                    Spacer(
                        modifier = modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(LiftTheme.colorScheme.no8)

                    )
                }

                UserDetailUiState.Loading -> {
                    Spacer(
                        modifier = modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(SkeletonBrush())

                    )
                }

                is UserDetailUiState.Success -> {
                    GlideImage(
                        model = userDetailUiState.userDetail.profilePicture,
                        contentDescription = "image description",
                        modifier = modifier.size(38.dp)
                    )
                }
            }
        }
    )
}