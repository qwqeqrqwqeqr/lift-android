package com.gradation.lift.feature.my_info.my_info.component.profile_detail_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.user.UserDetail
import com.gradation.lift.ui.utils.toText

@ExperimentalGlideComposeApi
@Composable
internal fun ProfileDetailView(
    modifier: Modifier = Modifier,
    userDetail: UserDetail,
    signOut: () -> Unit,
    navigateMyInfoToUpdateProfileInMyInfoGraph: () -> Unit
) {
    Row(
        modifier = modifier.padding(vertical = 16.dp)
    ) {
        Box {
            GlideImage(
                model = userDetail.profilePicture,
                contentDescription = "profile",
                modifier = modifier
                    .size(72.dp)
                    .clip(CircleShape)
            )
            Box(
                modifier = modifier
                    .background(Color.Transparent, CircleShape)
                    .size(72.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                IconButton(
                    onClick = navigateMyInfoToUpdateProfileInMyInfoGraph,
                    modifier = modifier
                        .background(LiftTheme.colorScheme.no1, CircleShape)
                        .size(24.dp)
                        .padding(2.dp)
                ) {
                    Icon(
                        modifier = modifier
                            .size(12.dp),
                        painter = painterResource(LiftIcon.Pencil),
                        contentDescription = "",
                        tint = Color.Unspecified,
                    )
                }
            }
        }
        Spacer(modifier = modifier.padding(8.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.padding(vertical = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = userDetail.name,
                        style = LiftTheme.typography.no1,
                        color = LiftTheme.colorScheme.no11,
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    Text(
                        text = "${userDetail.height.toText()}cm/${userDetail.weight.toText()}kg",
                        style = LiftTheme.typography.no4,
                        color = LiftTheme.colorScheme.no11
                    )
                }
                Box(
                    modifier = modifier
                        .background(
                            LiftTheme.colorScheme.no1, RoundedCornerShape(6.dp)
                        )
                        .padding(5.dp)
                        .noRippleClickable { signOut() }
                ) {
                    Text(
                        text = "로그아웃",
                        style = LiftTheme.typography.no6,
                        color = LiftTheme.colorScheme.no2
                    )
                }
            }
        }

    }
}

