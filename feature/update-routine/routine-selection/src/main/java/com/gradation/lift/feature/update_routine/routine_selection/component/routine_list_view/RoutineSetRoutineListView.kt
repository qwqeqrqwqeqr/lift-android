package com.gradation.lift.feature.update_routine.routine_selection.component.routine_list_view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.update_routine.routine_selection.component.routine_view.RoutineListView
import com.gradation.lift.feature.update_routine.routine_selection.data.model.RoutineSetRoutineSelection


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RoutineSetRoutineListView(
    modifier: Modifier = Modifier,
    routineSetRoutineSelection: List<RoutineSetRoutineSelection>,
    updateOpenedRoutineIdList: (Int, Boolean) -> Unit,
    updateSelectedRoutine: (RoutineSetRoutineSelection) -> Unit,
    navigateRoutineSelectionToRoutineSetInUpdateRoutineGraph: () -> Unit,
) {
    Surface(color = LiftTheme.colorScheme.no17) {
        LazyColumn(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(routineSetRoutineSelection) { _, routineSetRoutine ->
                Column(
                    modifier = modifier
                        .border(
                            width = 1.dp,
                            color = LiftTheme.colorScheme.no8,
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                        .clip(RoundedCornerShape(size = 12.dp))
                        .background(LiftTheme.colorScheme.no5)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .noRippleClickable {
                                updateSelectedRoutine(routineSetRoutine)
                                navigateRoutineSelectionToRoutineSetInUpdateRoutineGraph()
                            }
                            .padding(
                                start = 16.dp,
                                top = 12.dp,
                                bottom = 12.dp,
                                end = 24.dp
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
                        Spacer(modifier = modifier.padding(8.dp))
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center,
                            modifier = modifier.weight(1f)
                        ) {
                            Text(
                                text = routineSetRoutine.name,
                                style = LiftTheme.typography.no2,
                                color = LiftTheme.colorScheme.no4,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                            Text(
                                text = routineSetRoutine.description,
                                style = LiftTheme.typography.no4,
                                color = LiftTheme.colorScheme.no9,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }
                        Spacer(modifier = modifier.padding(4.dp))
                        Icon(
                            painterResource(id = LiftIcon.ChevronRight),
                            contentDescription = null,
                            modifier = modifier
                                .width(10.dp)
                                .height(16.dp)
                        )
                    }
                    routineSetRoutine.routine.forEach { routine ->
                        RoutineListView(
                            modifier,
                            routine,
                            updateOpenedRoutineIdList
                        )
                    }
                    Spacer(modifier = modifier.padding(4.dp))
                }
                Spacer(modifier = modifier.padding(2.dp))
            }
        }
    }
}

