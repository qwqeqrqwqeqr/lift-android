package com.gradation.lift.feature.register_detail.profile_picture.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.picture.UserProfilePicture

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfilePictureView(
    modifier: Modifier = Modifier,
    selectedProfilePicture: String,
    profilePictureList: List<UserProfilePicture>,
    updateSelectedProfile: (String) -> Unit
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(profilePictureList) { item ->
            if (item.url == selectedProfilePicture) {
                GlideImage(
                    model = item.url,
                    contentDescription = "",
                    modifier = modifier
                        .border(
                            width = 3.dp,
                            color = LiftTheme.colorScheme.no4,
                            shape = CircleShape
                        )
                        .clip(CircleShape)

                )
            } else {
                GlideImage(
                    model = item.url,
                    contentDescription = "",
                    modifier = modifier
                        .clip(CircleShape)
                        .noRippleClickable { updateSelectedProfile(item.url) }

                )
            }

        }
    }
}