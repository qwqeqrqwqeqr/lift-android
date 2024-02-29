package com.gradation.lift.feature.routineDetail.routine.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.component.chip.LiftDefaultChip
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.textField.LiftDefaultInputTextField
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.date.getWeekdayEntries
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.model.model.routine.RoutineSetRoutine

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RoutineContentView(
    modifier: Modifier = Modifier,
    routineSetRoutine: RoutineSetRoutine,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LiftTheme.colorScheme.no5)
            .padding(LiftTheme.space.paddingSpace),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
    ) {
        LiftText(
            textStyle = LiftTextStyle.No3,
            text = "루틴 프로필",
            color = LiftTheme.colorScheme.no10,
            textAlign = TextAlign.Start
        )
        GlideImage(
            modifier = modifier
                .size(LiftTheme.space.space96)
                .align(Alignment.CenterHorizontally),
            model = routineSetRoutine.picture,
            contentDescription = "routineProfilePicture"
        )
        LiftText(
            textStyle = LiftTextStyle.No3,
            text = "루틴 이름",
            color = LiftTheme.colorScheme.no10,
            textAlign = TextAlign.Start
        )
        LiftDefaultInputTextField(
            value = routineSetRoutine.name,
            onValueChange = {},
            enabled = false
        )



        if (routineSetRoutine.description.isNotEmpty()) {
            LiftText(
                textStyle = LiftTextStyle.No3,
                text = "루틴 설명",
                color = LiftTheme.colorScheme.no10,
                textAlign = TextAlign.Start
            )
            LiftDefaultInputTextField(
                value = routineSetRoutine.description,
                onValueChange = {},
                enabled = false
            )
        }
        LiftText(
            textStyle = LiftTextStyle.No3,
            text = "루틴 요일",
            color = LiftTheme.colorScheme.no10,
            textAlign = TextAlign.Start
        )
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            item {
                LiftDefaultChip(
                    modifier = modifier,
                    text = "전체",
                    isSelected = (routineSetRoutine.weekday.size == getWeekdayEntries().size),
                    enabled = false
                )
            }
            items(getWeekdayEntries()) {
                LiftDefaultChip(
                    modifier = modifier,
                    text = it.getWeekdayName(),
                    isSelected = (routineSetRoutine.weekday.contains(it) && routineSetRoutine.weekday.size != getWeekdayEntries().size),
                    enabled = false
                )
            }
        }
        LiftText(
            textStyle = LiftTextStyle.No3,
            text = "루틴 라벨",
            color = LiftTheme.colorScheme.no10,
            textAlign = TextAlign.Start
        )

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12),
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf(
                Triple(
                    Label.LABEL1,
                    LiftIcon.RoutineLabel1,
                    LiftIcon.CheckedRoutineLabel1
                ),
                Triple(
                    Label.LABEL2,
                    LiftIcon.RoutineLabel2,
                    LiftIcon.CheckedRoutineLabel2
                ),
                Triple(
                    Label.LABEL3,
                    LiftIcon.RoutineLabel3,
                    LiftIcon.CheckedRoutineLabel3
                ),
                Triple(
                    Label.LABEL4,
                    LiftIcon.RoutineLabel4,
                    LiftIcon.CheckedRoutineLabel4
                ),
                Triple(
                    Label.LABEL5,
                    LiftIcon.RoutineLabel5,
                    LiftIcon.CheckedRoutineLabel5
                )
            ).forEach {
                if (routineSetRoutine.label.contains(it.first))
                    Icon(
                        modifier = modifier.size(LiftTheme.space.space24),
                        painter = painterResource(id = it.third),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                else Icon(
                    modifier = modifier.size(LiftTheme.space.space24),
                    painter = painterResource(id = it.second),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            }
        }
        LiftText(
            textStyle = LiftTextStyle.No3,
            text = "운동 목록",
            color = LiftTheme.colorScheme.no10,
            textAlign = TextAlign.Start
        )
    }
}