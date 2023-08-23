package com.gradation.lift.feature.home.component.weekdate_routine_view.routine_view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.routine.RoutineSetRoutine

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
internal fun RoutineListView(
    modifier: Modifier = Modifier,
    navController: NavController,
    routineSetRoutineList: List<RoutineSetRoutine>,
    updateRoutineSetIdKey: (NavController, Int) -> Unit,
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
    navigateMainGraphToWorkGraph: () -> Unit,
) {
    Column(modifier=modifier.fillMaxHeight()) {
        Spacer(modifier = modifier.padding(7.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.End),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LiftOutlineButton(
                modifier = modifier
                    .height(32.dp),
                shape = RoundedCornerShape(6.dp),
                contentPadding = PaddingValues(
                    start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp
                ),
                onClick = navigateMainGraphToCreateRoutineGraph,
            ) {
                Text(
                    text = "추가",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no4,
                )
                Spacer(modifier = modifier.padding(2.dp))
                Icon(
                    painter = painterResource(id = LiftIcon.Plus),
                    contentDescription = null,
                )
            }

            LiftOutlineButton(
                modifier = modifier
                    .height(32.dp),
                shape = RoundedCornerShape(6.dp),
                contentPadding = PaddingValues(
                    start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp
                ),
                onClick = {
                    //TODO 수정 기능 추가 시 연동 예정 2023-08-18 19:40:35
                },
            ) {
                Text(
                    text = "수정",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no4,
                )
                Spacer(modifier = modifier.padding(2.dp))
                Icon(
                    painter = painterResource(id = LiftIcon.ChevronRight),
                    contentDescription = null,
                )
            }
        }
        Spacer(modifier = modifier.padding(5.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(
                8.dp,
                alignment = Alignment.CenterVertically
            ),
        ) {
            routineSetRoutineList.forEach { routineSetRoutine ->
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(LiftTheme.colorScheme.no5)
                        .border(
                            width = 1.dp,
                            color = LiftTheme.colorScheme.no8,
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .noRippleClickable(
                            onClick = {
                                updateRoutineSetIdKey(navController, routineSetRoutine.id)
                                navigateMainGraphToWorkGraph()
                            }
                        )
                ) {
                    GlideImage(
                        model = routineSetRoutine.picture,
                        contentDescription = "routine set picture",
                        modifier = modifier
                            .clip(shape = RoundedCornerShape(6.dp))
                            .size(38.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = routineSetRoutine.name,
                            style = LiftTheme.typography.no2,
                            color = LiftTheme.colorScheme.no9,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = routineSetRoutine.description,
                            style = LiftTheme.typography.no4,
                            color = LiftTheme.colorScheme.no9,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}



