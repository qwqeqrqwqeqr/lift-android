package com.gradation.lift.feature.home.component.profile_view.profile_detail_view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
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
import com.gradation.lift.designsystem.component.LiftIconButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.user.UserDetail

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
internal fun ProfileDetailView(
    modifier: Modifier = Modifier,
    userDetail: UserDetail,
    onClickAlarm: () -> Unit,
    onClickType: () -> Unit,
) {


    GlideImage(
        model = userDetail.profilePicture,
        contentDescription = "profile",
        modifier = modifier
            .size(72.dp)
            .clip(CircleShape)
    )
    Spacer(modifier = modifier.padding(10.dp))
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(vertical = 10.dp)
            .fillMaxHeight()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                text = userDetail.name,
                style = LiftTheme.typography.no1,
                color = LiftTheme.colorScheme.no5,
            )
            Spacer(modifier = modifier.padding(4.dp))
            Text(
                text = "${userDetail.height}cm/${userDetail.weight}kg",
                style = LiftTheme.typography.no4,
                color = LiftTheme.colorScheme.no5
            )
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //TODO implement click Type
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "운동 초심자",
                    style = LiftTheme.typography.no4,
                    color = LiftTheme.colorScheme.no5
                )
                Spacer(modifier = modifier.padding(1.dp))
                Icon(
                    painter = painterResource(LiftIcon.ChevronRightSharp),
                    contentDescription = "",
                    tint = LiftTheme.colorScheme.no5
                )
            }


            LiftIconButton(
                onClick = onClickAlarm,
                modifier = modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(LiftIcon.Bell),
                    contentDescription = "",
                    tint = Color.Unspecified,
                )
            }
        }

    }
}






