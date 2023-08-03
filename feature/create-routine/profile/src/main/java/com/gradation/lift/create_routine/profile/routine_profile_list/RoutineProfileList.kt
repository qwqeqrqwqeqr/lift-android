package com.gradation.lift.create_routine.profile.routine_profile_list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.create_routine.profile.CreateRoutineProfileScreen
import com.gradation.lift.create_routine.profile.RoutineSetCategoryPicture
import com.gradation.lift.create_routine.profile.RoutineSetPictureUiState
import com.gradation.lift.create_routine.profile.SelectedPicture
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RoutineProfileList(
    modifier: Modifier = Modifier,
    updateSelectedPicture: (String) -> Unit,
    routineSetPictureList: List<RoutineSetCategoryPicture>,
    selectedPicture: State<String>,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(72.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item(span = { GridItemSpan(4) }) {
            Column {
                if (selectedPicture.value.isNotBlank()) {
                    GlideImage(
                        model = selectedPicture.value,
                        contentDescription = "selected url",
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
        }
        item(span = { GridItemSpan(4) }) {
            Spacer(modifier = modifier.padding(8.dp))
        }
        routineSetPictureList.forEach { routineSetCategoryPicture ->
            item(
                span = { GridItemSpan(4) }
            ) {
                Text(
                    text = routineSetCategoryPicture.category,
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no3
                )
                Spacer(modifier = modifier.padding(4.dp))
            }
            items(routineSetCategoryPicture.picture) { picture ->
                GlideImage(
                    model = picture.url,
                    contentDescription = "selected url",
                    modifier = modifier
                        .size(72.dp)
                        .border(
                            width = if (picture.selected) 3.dp else 0.dp,
                            color = if (picture.selected) LiftTheme.colorScheme.no4 else Color.Unspecified,
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                        .clickable(
                            onClick = {
                                updateSelectedPicture(picture.url)
                            }
                        )
                )
            }
        }

    }

}



@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun CreateRoutineProfileScreePreview() {

    LiftMaterialTheme {
        CreateRoutineProfileScreen(
            modifier = Modifier,
            onBackClickTopBar = {},
            onClickRegisterButton = {},
            updateSelectedPicture = {},
            routineSetPictureUiState = RoutineSetPictureUiState.Success(
                listOf(
                    RoutineSetCategoryPicture(
                        category = "카테고리1",
                        picture = listOf(
                            SelectedPicture(""),
                            SelectedPicture(""),
                            SelectedPicture(""),
                            SelectedPicture(""),
                            SelectedPicture(""),
                        )
                    ),
                    RoutineSetCategoryPicture(
                        category = "카테고리2",
                        picture = listOf(
                            SelectedPicture(""),
                            SelectedPicture(""),
                            SelectedPicture(""),
                            SelectedPicture(""),
                            SelectedPicture("", true),
                        )
                    )
                )
            ),
            selectedPicture = mutableStateOf(""),
        )
    }
}