package com.gradation.lift.feature.update_routine.profile_picture

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.update_routine.profile_picture.component.routine_profile_list.EmptyRoutineProfilePictureList
import com.gradation.lift.feature.update_routine.profile_picture.component.routine_profile_list.RoutineProfilePictureList
import com.gradation.lift.feature.update_routine.profile_picture.data.model.RoutineSetCategoryPicture
import com.gradation.lift.feature.update_routine.profile_picture.data.model.SelectedPicture
import com.gradation.lift.feature.update_routine.profile_picture.data.state.RoutineSetPictureUiState
import com.gradation.lift.feature.update_routine.profile_picture.data.viewmodel.UpdateRoutineProfilePictureViewModel
import com.gradation.lift.feature.update_routine.routine_selection.data.viewmodel.UpdateRoutineSharedViewModel
import com.gradation.lift.navigation.Router.UPDATE_ROUTINE_GRAPH_NAME


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun UpdateRoutineProfilePictureRoute(
    navController: NavController,
    navigateProfilePictureToRoutineSetInUpdateRoutineGraph: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateRoutineProfilePictureViewModel = hiltViewModel(),
) {
    val updateRoutineBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(UPDATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: UpdateRoutineSharedViewModel = hiltViewModel(updateRoutineBackStackEntry)
    val selectedPicture: String by viewModel.selectedPicture.collectAsStateWithLifecycle()
    val updateSelectedPicture: (String) -> Unit = viewModel.updateSelectedPicture()
    val updateRoutineSetPicture: (String) -> Unit = sharedViewModel.updateRoutineSetPicture()
    val routineSetPictureUiState: RoutineSetPictureUiState by viewModel.routineSetPictureUiState.collectAsStateWithLifecycle()


    UpdateRoutineProfilePictureScreen(
        modifier,
        selectedPicture,
        updateSelectedPicture,
        updateRoutineSetPicture,
        navigateProfilePictureToRoutineSetInUpdateRoutineGraph,
        routineSetPictureUiState
    )

    BackHandler(onBack = navigateProfilePictureToRoutineSetInUpdateRoutineGraph)
}


@Composable
fun UpdateRoutineProfilePictureScreen(
    modifier: Modifier = Modifier,
    selectedPicture: String,
    updateSelectedPicture: (String) -> Unit,
    updateRoutineSetPicture: (String) -> Unit,
    navigateProfilePictureToRoutineSetInUpdateRoutineGraph: () -> Unit,
    routineSetPictureUiState: RoutineSetPictureUiState,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "프로필 등록하기",
                onBackClickTopBar = navigateProfilePictureToRoutineSetInUpdateRoutineGraph,
            )
        }
    ) { padding ->
        Surface(
            color = LiftTheme.colorScheme.no5,
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                when (routineSetPictureUiState) {
                    RoutineSetPictureUiState.Fail -> {}
                    RoutineSetPictureUiState.Loading -> {
                        EmptyRoutineProfilePictureList(modifier)
                    }
                    is RoutineSetPictureUiState.Success -> {
                        RoutineProfilePictureList(
                            updateSelectedPicture = updateSelectedPicture,
                            routineSetPictureList = routineSetPictureUiState.routineSetPictureList,
                            updateRoutineSetPicture=updateRoutineSetPicture,
                            navigateProfilePictureToRoutineSetInUpdateRoutineGraph = navigateProfilePictureToRoutineSetInUpdateRoutineGraph,
                            selectedPicture = selectedPicture,
                        )
                    }
                }
            }

        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun UpdateRoutineProfilePictureScreenPreview() {

    LiftMaterialTheme {
        UpdateRoutineProfilePictureScreen(
            modifier = Modifier,
            selectedPicture = "",
            updateRoutineSetPicture = {},
            updateSelectedPicture = {},
            navigateProfilePictureToRoutineSetInUpdateRoutineGraph = {},
            routineSetPictureUiState = RoutineSetPictureUiState.Success(
                listOf(
                    RoutineSetCategoryPicture(
                        category = "카테고리1",
                        pictureList = listOf(
                            SelectedPicture(""),
                            SelectedPicture(""),
                            SelectedPicture(""),
                            SelectedPicture(""),
                            SelectedPicture(""),
                        )
                    ),
                    RoutineSetCategoryPicture(
                        category = "카테고리2",
                        pictureList = listOf(
                            SelectedPicture(""),
                            SelectedPicture(""),
                            SelectedPicture(""),
                            SelectedPicture(""),
                            SelectedPicture("", true),
                        )
                    )
                )
            ),
        )
    }
}

