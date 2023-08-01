@file:OptIn(ExperimentalGlideComposeApi::class)

package com.gradation.lift.create_routine.profile

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.create_routine.data.CreateRoutineSharedViewModel
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_GRAPH_NAME

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun CreateRoutineProfileRoute(
    navController: NavController,
    navigateCreateRoutineProfileToRoot: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineProfileViewModel = hiltViewModel()
) {

    val crateRoutineBackStackEntry =
        remember { navController.getBackStackEntry(CREATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(crateRoutineBackStackEntry)


    val updatePicture = sharedViewModel.updatePicture()
    val routineSetPictureUiState: RoutineSetPictureUiState by viewModel.routineSetPictureUiState.collectAsStateWithLifecycle()
    val selectedPicture = sharedViewModel.picture.collectAsStateWithLifecycle()


    CreateRoutineProfileScreen(
        modifier = modifier,
        onBackClickTopBar = navigateCreateRoutineProfileToRoot,
        onClickRegisterButton = navigateCreateRoutineProfileToRoot,
        updatePicture = updatePicture,
        routineSetPictureUiState = routineSetPictureUiState,
        selectedPicture = selectedPicture
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun CreateRoutineProfileScreen(
    modifier: Modifier = Modifier,
    onBackClickTopBar: () -> Unit,
    onClickRegisterButton: () -> Unit,
    updatePicture: (String) -> Unit,
    routineSetPictureUiState: RoutineSetPictureUiState,
    selectedPicture: State<String>
) {
    Surface(
        color = LiftTheme.colorScheme.no5
    ) {
        Scaffold(
            topBar = {
                LiftBackTopBar(
                    title = "프로필 등록하기",
                    onBackClickTopBar = onBackClickTopBar,
                )
            }, modifier = modifier

                .fillMaxSize()
        ) { padding ->
            Column(
                modifier = modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxWidth()

            ) {
                when (routineSetPictureUiState) {
                    RoutineSetPictureUiState.Fail -> {}
                    RoutineSetPictureUiState.Loading -> {}
                    is RoutineSetPictureUiState.Success -> {
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
                        Spacer(modifier = modifier.padding(8.dp))
                        routineSetPictureUiState.routineSetPictureList.forEach { routineSetCategoryPicture ->
                            Text(
                                text = routineSetCategoryPicture.category,
                                style = LiftTheme.typography.no3,
                                color = LiftTheme.colorScheme.no3
                            )
                            Spacer(modifier = modifier.padding(4.dp))
                            LazyVerticalGrid(
                                columns = GridCells.Adaptive(minSize = 64.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                itemsIndexed(items = routineSetCategoryPicture.picture) { index, picture ->
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
                                                    updatePicture(picture.url)
                                                }
                                            )
                                    )
                                }
                            }
                            Spacer(modifier = modifier.padding(9.dp))
                        }
                        Spacer(modifier = modifier.padding(18.dp))
                        LiftButton(
                            modifier = modifier.fillMaxWidth(),
                            onClick = onClickRegisterButton,
                        ) {
                            Text(
                                text = "등록하기",
                                style = LiftTheme.typography.no3,
                                color = LiftTheme.colorScheme.no5,
                            )
                        }
                    }
                }
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
            updatePicture = {},
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
            selectedPicture = mutableStateOf("")
        )
    }
}