package com.gradation.lift.feature.createRoutine.routineSet.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.ui.modifier.noRippleClickable

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RoutineSetProfilePictureView(
    modifier: Modifier = Modifier,
    currentRoutineSetRoutine: RoutineSetRoutine,
    navigateRoutineSetToProfilePictureInCreateRoutineGraph: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16),
    ) {
        LiftText(
            textStyle = LiftTextStyle.No3,
            text = "루틴 프로필",
            color = LiftTheme.colorScheme.no3,
            textAlign = TextAlign.Start
        )

        if (currentRoutineSetRoutine.picture.isEmpty())
            Box(
                modifier = modifier
                    .background(
                        color = LiftTheme.colorScheme.no1,
                        shape = RoundedCornerShape(size = 12.dp)
                    )
                    .size(LiftTheme.space.space96)
                    .align(Alignment.CenterHorizontally)
                    .noRippleClickable {
                        navigateRoutineSetToProfilePictureInCreateRoutineGraph()
                    },
            ){
                Icon(
                    modifier = modifier
                        .align(Alignment.Center)
                        .size(LiftTheme.space.space32),
                    painter = painterResource(id = LiftIcon.Plus),
                    contentDescription = "",
                    tint = LiftTheme.colorScheme.no6
                )
            }
        else
            Box(
                modifier = modifier
                    .background(
                        color = LiftTheme.colorScheme.no1,
                        shape = RoundedCornerShape(size = 12.dp)
                    )
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                GlideImage(
                    modifier = modifier.size(LiftTheme.space.space96),
                    model = currentRoutineSetRoutine.picture,
                    contentDescription = "RoutineProfilePicture"
                )
                Box(
                    modifier = modifier
                        .offset(x = LiftTheme.space.space8, y = LiftTheme.space.space8)
                        .size(LiftTheme.space.space32)
                        .background(LiftTheme.colorScheme.no1, CircleShape)
                        .align(Alignment.BottomEnd)
                        .noRippleClickable {
                            navigateRoutineSetToProfilePictureInCreateRoutineGraph()
                        }
                ) {
                    Icon(
                        modifier = modifier
                            .align(Alignment.Center)
                            .size(LiftTheme.space.space16),
                        painter = painterResource(id = LiftIcon.Pencil),
                        contentDescription = "RoutineProfilePictureEdit",
                        tint = LiftTheme.colorScheme.no6
                    )
                }

            }

    }
}