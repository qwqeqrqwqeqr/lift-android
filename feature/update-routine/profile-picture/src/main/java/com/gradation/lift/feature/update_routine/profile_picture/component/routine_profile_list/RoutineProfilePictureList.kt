package com.gradation.lift.feature.update_routine.profile_picture.component.routine_profile_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.ui.modifier.noRippleClickable
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.update_routine.profile_picture.data.model.RoutineSetCategoryPicture


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RoutineProfilePictureList(
    modifier: Modifier = Modifier,
    routineSetPictureList: List<RoutineSetCategoryPicture>,
    updateRoutineSetPicture: (String) -> Unit,
    navigateProfilePictureToRoutineSetInUpdateRoutineGraph: () -> Unit,
    selectedPicture: String
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(48.dp)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (selectedPicture.isNotBlank()) {
                GlideImage(
                    model = selectedPicture,
                    contentDescription = "selectedUrl",
                    modifier = modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .clip(shape = RoundedCornerShape(size = 12.dp))
                        .size(96.dp)
                )
            } else {
                Box(
                    modifier = modifier
                        .background(
                            color = LiftTheme.colorScheme.no1,
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                        .align(alignment = Alignment.CenterHorizontally)
                        .size(96.dp)
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(72.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
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
                    Spacer(modifier = modifier.padding(16.dp))
                }
                items(routineSetCategoryPicture.pictureList) { picture ->
                    GlideImage(
                        model = picture,
                        contentDescription = "selected url",
                        modifier = modifier
                            .size(72.dp)
                            .border(
                                width = if (picture == selectedPicture) 3.dp else 0.dp,
                                color = if (picture == selectedPicture) LiftTheme.colorScheme.no4 else Color.Unspecified,
                                shape = RoundedCornerShape(size = 12.dp)
                            )
                            .noRippleClickable {
                                updateRoutineSetPicture(picture)
                                navigateProfilePictureToRoutineSetInUpdateRoutineGraph()
                            }
                    )
                }
            }
        }
    }
}

