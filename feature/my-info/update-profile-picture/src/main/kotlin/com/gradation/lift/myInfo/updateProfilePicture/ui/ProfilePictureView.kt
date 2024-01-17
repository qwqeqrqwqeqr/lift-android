package com.gradation.lift.myInfo.updateProfilePicture.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.ui.modifier.noRippleClickable
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.picture.UserProfilePicture

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfilePictureView(
    modifier: Modifier = Modifier,
    selectedProfilePicture: String,
    profilePictureList: List<UserProfilePicture>,
    updateSelectedProfilePicture: (String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
    ) {
        items(profilePictureList) { item ->
            val borderColor: Color by animateColorAsState(
                if (item.url == selectedProfilePicture) LiftTheme.colorScheme.no4
                else Color.Transparent,
                label = "profilePictureBorderColor"
            )
            GlideImage(
                model = item.url,
                contentDescription = "profilePicture",
                modifier = modifier
                    .border(
                        width = LiftTheme.space.space2,
                        color = borderColor,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .noRippleClickable { updateSelectedProfilePicture(item.url) }

            )
        }
    }
}