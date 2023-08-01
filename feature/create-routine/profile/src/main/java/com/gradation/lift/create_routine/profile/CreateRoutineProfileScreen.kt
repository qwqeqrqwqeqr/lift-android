@file:OptIn(ExperimentalGlideComposeApi::class)

package com.gradation.lift.create_routine.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.gradation.lift.create_routine.profile.routine_profile_list.EmptyRoutineProfileList
import com.gradation.lift.create_routine.profile.routine_profile_list.RoutineProfileList
import com.gradation.lift.designsystem.component.LiftBackTopBar
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


    val updateSelectedPicture = viewModel.updateSelectedPicture()
    val selectedPicture = viewModel.selectedPicture.collectAsStateWithLifecycle()
    val routineSetPictureUiState: RoutineSetPictureUiState by viewModel.routineSetPictureUiState.collectAsStateWithLifecycle()


    CreateRoutineProfileScreen(
        modifier = modifier,
        onBackClickTopBar = navigateCreateRoutineProfileToRoot,
        onClickRegisterButton = {
            sharedViewModel.updatePicture(selectedPicture.value)
            navigateCreateRoutineProfileToRoot()
        },
        updateSelectedPicture = updateSelectedPicture,
        routineSetPictureUiState = routineSetPictureUiState,
        selectedPicture = selectedPicture,
    )
}

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun CreateRoutineProfileScreen(
    modifier: Modifier = Modifier,
    onBackClickTopBar: () -> Unit,
    onClickRegisterButton: () -> Unit,
    updateSelectedPicture: (String) -> Unit,
    routineSetPictureUiState: RoutineSetPictureUiState,
    selectedPicture: State<String>,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "프로필 등록하기",
                onBackClickTopBar = onBackClickTopBar,
            )
        },
    ) { padding ->
        Box(
            modifier
                .background(LiftTheme.colorScheme.no5)
                .fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .padding(padding)
                    .padding(16.dp)

            ) {
                when (routineSetPictureUiState) {
                    RoutineSetPictureUiState.Fail -> {}
                    RoutineSetPictureUiState.Loading -> {
                        EmptyRoutineProfileList(modifier)
                    }
                    is RoutineSetPictureUiState.Success -> {
                        RoutineProfileList(
                            onClickRegisterButton = onClickRegisterButton,
                            updateSelectedPicture = updateSelectedPicture,
                            routineSetPictureList = routineSetPictureUiState.routineSetPictureList,
                            selectedPicture = selectedPicture
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

