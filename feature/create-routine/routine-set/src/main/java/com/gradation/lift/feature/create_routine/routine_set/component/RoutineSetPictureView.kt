package com.gradation.lift.feature.create_routine.routine_set.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RoutineSetPictureView(
    modifier: Modifier = Modifier,
    navigateRoutineSetToProfile: () -> Unit,
    routineSetPicture: String,
) {
    Column(modifier = modifier.fillMaxWidth()){
        Text(
            text = "루틴리스트 프로필",
            style = LiftTheme.typography.no3,
            color = LiftTheme.colorScheme.no3,
            modifier= modifier.align(Alignment.Start)
        )
        Spacer(modifier = modifier.padding(8.dp))

        Box(
            modifier =modifier
                .background(
                    color = LiftTheme.colorScheme.no1,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .align(Alignment.CenterHorizontally)
                .size(96.dp)
                .noRippleClickable {
                    navigateRoutineSetToProfile()
                }, contentAlignment = Alignment.Center

        ) {
            if (routineSetPicture.isBlank()) {
                Image(
                    modifier = modifier.size(32.dp).align(Alignment.Center),
                    painter = painterResource(id = LiftIcon.Plus),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(LiftTheme.colorScheme.no6)
                )
            } else {
                GlideImage(
                    model = routineSetPicture,
                    contentDescription = "",
                    modifier = modifier.fillMaxSize().align(Alignment.Center)
                )
            }
        }
    }

}