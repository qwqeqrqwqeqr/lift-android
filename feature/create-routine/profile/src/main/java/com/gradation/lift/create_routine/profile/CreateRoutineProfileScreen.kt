package com.gradation.lift.create_routine.profile

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
import com.gradation.lift.create_routine.profile.data.model.RoutineSetCategoryPicture
import com.gradation.lift.create_routine.profile.data.state.RoutineSetPictureUiState
import com.gradation.lift.create_routine.profile.data.viewmodel.CreateRoutineProfileViewModel
import com.gradation.lift.create_routine.profile.component.routine_profile_list.LoadingRoutineProfileList
import com.gradation.lift.create_routine.profile.component.routine_profile_list.RoutineProfileList
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.create_routine.routine_set.data.viewmodel.CreateRoutineSharedViewModel
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_GRAPH_NAME

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun CreateRoutineProfileRoute(
    navController: NavController,
    navigateProfileToRoutineSetInCreateRoutineGraph: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineProfileViewModel = hiltViewModel(),
) {
    val crateRoutineBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(CREATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(crateRoutineBackStackEntry)
    val selectedPicture: String by sharedViewModel.routineSetPicture.collectAsStateWithLifecycle()
    val updateRoutineSetPicture: (String) -> Unit = sharedViewModel.updateRoutineSetPicture()
    val routineSetPictureUiState: RoutineSetPictureUiState by viewModel.routineSetPictureUiState.collectAsStateWithLifecycle()


    CreateRoutineProfileScreen(
        modifier,
        selectedPicture,
        updateRoutineSetPicture,
        navigateProfileToRoutineSetInCreateRoutineGraph,
        routineSetPictureUiState
    )

    BackHandler(onBack = navigateProfileToRoutineSetInCreateRoutineGraph)
}


@Composable
fun CreateRoutineProfileScreen(
    modifier: Modifier = Modifier,
    selectedPicture: String,
    updateRoutineSetPicture: (String) -> Unit,
    navigateProfileToRoutineSetInCreateRoutineGraph: () -> Unit,
    routineSetPictureUiState: RoutineSetPictureUiState,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "프로필 등록하기",
                onBackClickTopBar = navigateProfileToRoutineSetInCreateRoutineGraph,
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
                        LoadingRoutineProfileList(modifier)
                    }

                    is RoutineSetPictureUiState.Success -> {
                        RoutineProfileList(
                            routineSetPictureList = routineSetPictureUiState.routineSetPictureList,
                            updateRoutineSetPicture = updateRoutineSetPicture,
                            navigateProfileToRoutineSetInCreateRoutineGraph = navigateProfileToRoutineSetInCreateRoutineGraph,
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
fun CreateRoutineProfileScreePreview() {

    LiftMaterialTheme {
        CreateRoutineProfileScreen(
            modifier = Modifier,
            selectedPicture = "",
            updateRoutineSetPicture = {},
            navigateProfileToRoutineSetInCreateRoutineGraph = {},
            routineSetPictureUiState = RoutineSetPictureUiState.Success(
                listOf(
                    RoutineSetCategoryPicture(
                        category = "카테고리1",
                        pictureList = listOf(
                            "", "", "", "", ""
                        )
                    ),
                    RoutineSetCategoryPicture(
                        category = "카테고리2",
                        pictureList = listOf(
                            "", "", "", "", ""
                        )
                    )
                )
            ),
        )
    }
}

