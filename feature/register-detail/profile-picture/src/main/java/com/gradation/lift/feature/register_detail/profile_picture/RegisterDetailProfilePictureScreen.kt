package com.gradation.lift.feature.register_detail.profile_picture

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
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
import com.gradation.lift.designsystem.component.LiftErrorSnackBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.register_detail.name.data.CreateUserDetailState
import com.gradation.lift.feature.register_detail.name.data.RegisterDetailSharedViewModel
import com.gradation.lift.feature.register_detail.profile_picture.component.*
import com.gradation.lift.feature.register_detail.profile_picture.component.CompleteDialog
import com.gradation.lift.feature.register_detail.profile_picture.data.RegisterDetailProfilePictureViewModel
import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.navigation.Router


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
internal fun RegisterDetailProfilePictureRoute(
    navController: NavController,
    navigateRegisterDetailGraphToHomeGraph: () -> Unit,
    navigateProfilePictureToHeightWeight: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterDetailProfilePictureViewModel = hiltViewModel(),
) {
    val registerDetailBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(Router.REGISTER_DETAIL_GRAPH_NAME) }
    val sharedViewModel: RegisterDetailSharedViewModel = hiltViewModel(registerDetailBackStackEntry)


    val selectedProfilePicture: String by viewModel.selectedProfilePicture.collectAsStateWithLifecycle()
    val profilePictureList: List<UserProfilePicture> by viewModel.profilePictureList.collectAsStateWithLifecycle()
    val navigationCondition: Boolean by viewModel.navigationCondition.collectAsStateWithLifecycle()
    val onVisibleCompleteDialog: Boolean by viewModel.onVisibleCompleteDialog.collectAsStateWithLifecycle()

    val currentRegisterProgressNumber: Int by sharedViewModel.currentRegisterProgressNumber.collectAsStateWithLifecycle()
    val totalRegisterProgressNumber: Int by sharedViewModel.totalRegisterProgressNumber.collectAsStateWithLifecycle()

    val createUserDetailState: CreateUserDetailState by sharedViewModel.createUserDetailState.collectAsStateWithLifecycle()

    val updateSelectedProfile: (String) -> Unit = viewModel.updateSelectedProfile()
    val updateOnVisibleCompleteDialog: (Boolean) -> Unit = viewModel.updateOnVisibleCompleteDialog()

    val updateCreateUserDetailProfilePicture: (String) -> Unit =
        sharedViewModel.updateCreateUserDetailProfilePicture()
    val updateCurrentRegisterProgressNumber: (Int) -> Unit =
        sharedViewModel.updateCurrentRegisterProgressNumber()
    val updateCreateUserDetailState: (CreateUserDetailState) -> Unit =
        sharedViewModel.updateCreateUserDetailState()
    val createUserDetail: () -> Unit = sharedViewModel.createUserDetail()

    val snackbarHostState: SnackbarHostState by remember { mutableStateOf(SnackbarHostState()) }

    BackHandler(onBack = {
        updateCurrentRegisterProgressNumber(currentRegisterProgressNumber - 1)
        navigateProfilePictureToHeightWeight()
    })

    when (val createUserDetailResult = createUserDetailState) {
        is CreateUserDetailState.Fail -> {
            LaunchedEffect(true) {
                snackbarHostState.showSnackbar(
                    message = createUserDetailResult.message, duration = SnackbarDuration.Short
                )
                updateCreateUserDetailState(CreateUserDetailState.None)
            }
        }
        CreateUserDetailState.None -> {}
        CreateUserDetailState.Success -> {
            LaunchedEffect(true) {
                updateOnVisibleCompleteDialog(true)
            }
        }
    }

    RegisterDetailProfilePictureScreen(
        modifier,
        selectedProfilePicture,
        profilePictureList,
        navigationCondition,
        onVisibleCompleteDialog,
        currentRegisterProgressNumber,
        totalRegisterProgressNumber,
        updateSelectedProfile,
        updateCreateUserDetailProfilePicture,
        updateCurrentRegisterProgressNumber,
        createUserDetail,
        navigateRegisterDetailGraphToHomeGraph,
        navigateProfilePictureToHeightWeight,
        snackbarHostState
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RegisterDetailProfilePictureScreen(
    modifier: Modifier = Modifier,
    selectedProfilePicture: String,
    profilePictureList: List<UserProfilePicture>,
    navigationCondition: Boolean,
    onVisibleCompleteDialog: Boolean,
    currentRegisterProgressNumber: Int,
    totalRegisterProgressNumber: Int,
    updateSelectedProfile: (String) -> Unit,
    updateCreateUserDetailProfilePicture: (String) -> Unit,
    updateCurrentRegisterProgressNumber: (Int) -> Unit,
    createUserDetail: () -> Unit,
    navigateRegisterDetailGraphToHomeGraph: () -> Unit,
    navigateProfilePictureToHeightWeight: () -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Box {
        if (onVisibleCompleteDialog) {
            Surface(
                color = LiftTheme.colorScheme.no5.copy(alpha = 0.7f),
                modifier = modifier.fillMaxSize()
            ) {
                CompleteDialog(modifier, navigateRegisterDetailGraphToHomeGraph)
            }
        }
        Scaffold(
            topBar = {
                LiftBackTopBar(
                    title = "추가정보 입력",
                    onBackClickTopBar = {
                        updateCurrentRegisterProgressNumber(currentRegisterProgressNumber - 1)
                        navigateProfilePictureToHeightWeight()
                    }
                )
            },
            snackbarHost = {
                LiftErrorSnackBar(
                    modifier = modifier,
                    snackbarHostState = snackbarHostState
                )
            }
        ) { padding ->
            Surface(
                modifier = modifier
                    .padding(padding)
                    .fillMaxSize(),
                color = LiftTheme.colorScheme.no5,
            ) {
                Column(
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    ProgressNumberView(
                        modifier,
                        currentRegisterProgressNumber,
                        totalRegisterProgressNumber
                    )
                    Spacer(modifier = modifier.padding(14.dp))
                    HeaderView(modifier)
                    Spacer(modifier = modifier.padding(30.dp))
                    SelectedProfilePictureView(modifier, selectedProfilePicture)
                    Spacer(modifier = modifier.padding(13.dp))
                    ProfilePictureView(
                        modifier,
                        selectedProfilePicture,
                        profilePictureList,
                        updateSelectedProfile
                    )
                    Spacer(modifier = modifier.padding(18.dp))
                    NavigationView(
                        modifier,
                        selectedProfilePicture,
                        navigationCondition,
                        updateCreateUserDetailProfilePicture,
                        createUserDetail
                    )
                }
            }
        }
    }

}


@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun PreviewRegisterDetailProfilePictureScreen() {
    LiftMaterialTheme {
        RegisterDetailProfilePictureScreen(
            selectedProfilePicture = "",
            profilePictureList = listOf(),
            navigationCondition = true,
            onVisibleCompleteDialog = false,
            currentRegisterProgressNumber = 4,
            totalRegisterProgressNumber = 4,
            updateSelectedProfile = {},
            updateCreateUserDetailProfilePicture = {},
            updateCurrentRegisterProgressNumber = {},
            createUserDetail = {},
            navigateRegisterDetailGraphToHomeGraph = {},
            navigateProfilePictureToHeightWeight = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}
