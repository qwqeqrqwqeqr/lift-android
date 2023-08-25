
package com.gradation.lift.feature.my_info.my_info.component

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.user.UserDetail

@ExperimentalGlideComposeApi
@Composable
internal fun ProfileDetailView(
    modifier: Modifier = Modifier,
    userDetail: UserDetail,
    navigateMyInfoGraphToLoginGraph: () -> Unit,
) {
    Row(
        modifier = modifier.padding(vertical =16.dp)
    ) {
//        GlideImage(
//            model = userDetail.profilePicture,
//            contentDescription = "profile",
//            modifier = modifier
//                .size(72.dp)
//                .clip(CircleShape)
//        )
        Box(
            modifier = modifier
                .background(LiftTheme.colorScheme.no5)
                .size(72.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = modifier.padding(8.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
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
                        text = "${userDetail.height}cm/${userDetail.weight}kg",
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
                        .noRippleClickable {navigateMyInfoGraphToLoginGraph() }
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

