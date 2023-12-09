package com.gradation.lift.feature.updateRoutine.profilePicture.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.ui.modifier.noRippleClickable
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.updateRoutine.profilePicture.data.model.RoutineSetCategoryPicture
import com.gradation.lift.model.model.routine.RoutineSetRoutine


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RoutineProfilePictureList(
    modifier: Modifier = Modifier,
    currentRoutineSetRoutine: RoutineSetRoutine,
    routineSetPictureList: List<RoutineSetCategoryPicture>,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateProfilePictureToRoutineSetInUpdateRoutineGraph: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LiftTheme.colorScheme.no5)
            .padding(LiftTheme.space.paddingSpace),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space48)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlideImage(
                model = currentRoutineSetRoutine.picture,
                contentDescription = "selectedUrl",
                modifier = modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .clip(shape = RoundedCornerShape(size = LiftTheme.space.space12))
                    .size(LiftTheme.space.space96)
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space16,Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16,Alignment.CenterVertically),
        ) {
            routineSetPictureList.forEach { routineSetCategoryPicture ->
                item(
                    span = { GridItemSpan(4) }
                ) {
                    Text(
                        text = routineSetCategoryPicture.category,
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no3
                    )
                    Spacer(modifier = modifier.padding(LiftTheme.space.space16))
                }
                items(routineSetCategoryPicture.pictureList) { picture ->
                    GlideImage(
                        model = picture,
                        contentDescription = "selected url",
                        modifier = modifier
                            .size(LiftTheme.space.space72)
                            .noRippleClickable {
                                currentRoutineSetRoutineState.updateRoutineSetProfilePicture(picture)
                                navigateProfilePictureToRoutineSetInUpdateRoutineGraph()
                            }
                    )
                }
            }
        }
    }
}

