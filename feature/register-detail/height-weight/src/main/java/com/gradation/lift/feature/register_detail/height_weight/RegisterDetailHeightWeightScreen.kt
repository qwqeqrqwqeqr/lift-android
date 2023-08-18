package com.gradation.lift.feature.register_detail.height_weight

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.register_detail.height_weight.component.*
import com.gradation.lift.feature.register_detail.height_weight.component.HeightTextFieldView
import com.gradation.lift.feature.register_detail.height_weight.component.WeightTextFieldView
import com.gradation.lift.feature.register_detail.height_weight.data.RegisterDetailHeightWeightViewModel
import com.gradation.lift.feature.register_detail.name.data.RegisterDetailSharedViewModel
import com.gradation.lift.navigation.Router
import com.gradation.lift.ui.utils.DevicePreview

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
internal fun RegisterHeightWeightRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateHeightWeightToProfilePicture: () -> Unit,
    navigateHeightWeightToGender: () -> Unit,
    viewModel: RegisterDetailHeightWeightViewModel = hiltViewModel(),
) {
    val registerDetailBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(Router.REGISTER_DETAIL_GRAPH_NAME) }
    val sharedViewModel: RegisterDetailSharedViewModel = hiltViewModel(registerDetailBackStackEntry)

    val heightText: String by viewModel.heightText.collectAsStateWithLifecycle()
    val weightText: String by viewModel.weightText.collectAsStateWithLifecycle()
    val weightValidator: Validator by viewModel.weightValidator.collectAsStateWithLifecycle()
    val heightValidator: Validator by viewModel.heightValidator.collectAsStateWithLifecycle()
    val navigateCondition: Boolean by viewModel.navigateCondition.collectAsStateWithLifecycle()

    val currentRegisterProgressNumber: Int by sharedViewModel.currentRegisterProgressNumber.collectAsStateWithLifecycle()
    val totalRegisterProgressNumber: Int by sharedViewModel.totalRegisterProgressNumber.collectAsStateWithLifecycle()

    val updateWeightText: (String) -> Unit = viewModel.updateWeightText()
    val updateHeightText: (String) -> Unit = viewModel.updateHeightText()
    val updateCreateUserDetailHeight: (Float) -> Unit =
        sharedViewModel.updateCreateUserDetailHeight()
    val updateCreateUserDetailWeight: (Float) -> Unit =
        sharedViewModel.updateCreateUserDetailWeight()

    val updateCurrentRegisterProgressNumber: (Int) -> Unit =
        sharedViewModel.updateCurrentRegisterProgressNumber()

    val focusManager = LocalFocusManager.current

    RegisterDetailHeightWeightScreen(
        modifier,
        weightText,
        heightText,
        weightValidator,
        heightValidator,
        navigateCondition,
        currentRegisterProgressNumber,
        totalRegisterProgressNumber,
        updateWeightText,
        updateHeightText,
        updateCreateUserDetailHeight,
        updateCreateUserDetailWeight,
        updateCurrentRegisterProgressNumber,
        navigateHeightWeightToProfilePicture,
        navigateHeightWeightToGender,
        focusManager
    )

    BackHandler(onBack = {
        updateCurrentRegisterProgressNumber(currentRegisterProgressNumber - 1)
        navigateHeightWeightToGender()
    })

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RegisterDetailHeightWeightScreen(
    modifier: Modifier = Modifier,
    weightText: String,
    heightText: String,
    weightValidator: Validator,
    heightValidator: Validator,
    navigateCondition: Boolean,
    currentRegisterProgressNumber: Int,
    totalRegisterProgressNumber: Int,
    updateWeightText: (String) -> Unit,
    updateHeightText: (String) -> Unit,
    updateCreateUserDetailHeight: (Float) -> Unit,
    updateCreateUserDetailWeight: (Float) -> Unit,
    updateCurrentRegisterProgressNumber: (Int) -> Unit,
    navigateHeightWeightToProfilePicture: () -> Unit,
    navigateHeightWeightToGender: () -> Unit,
    focusManager: FocusManager,
) {

    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "추가정보 입력",
                onBackClickTopBar = {
                    updateCurrentRegisterProgressNumber(currentRegisterProgressNumber - 1)
                    navigateHeightWeightToGender()
                },
            )
        },
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
                    modifier, currentRegisterProgressNumber, totalRegisterProgressNumber
                )
                Spacer(modifier = modifier.padding(14.dp))
                HeaderView(modifier)
                Spacer(modifier = modifier.padding(15.dp))
                HeightTextFieldView(
                    modifier, heightText, heightValidator, updateHeightText, focusManager
                )
                WeightTextFieldView(
                    modifier, weightText, weightValidator, updateWeightText, focusManager
                )
                Spacer(modifier = modifier.padding(9.dp))
                NavigationView(
                    modifier,
                    navigateCondition,
                    currentRegisterProgressNumber,
                    weightText,
                    heightText,
                    updateCreateUserDetailHeight,
                    updateCreateUserDetailWeight,
                    updateCurrentRegisterProgressNumber,
                    navigateHeightWeightToProfilePicture
                )
            }
        }
    }
}


@DevicePreview
@Composable
fun RegisterDetailHeightWeightScreenPreview(
    modifier: Modifier = Modifier,
) {
    LiftMaterialTheme {
        RegisterDetailHeightWeightScreen(
            weightText = "",
            heightText = "",
            weightValidator = Validator(true, ""),
            heightValidator = Validator(false, "실패"),
            navigateCondition = true,
            currentRegisterProgressNumber = 3,
            totalRegisterProgressNumber = 4,
            updateWeightText = {},
            updateHeightText = {},
            updateCreateUserDetailHeight = {},
            updateCreateUserDetailWeight = {},
            updateCurrentRegisterProgressNumber = {},
            navigateHeightWeightToProfilePicture = {},
            navigateHeightWeightToGender = {},
            focusManager = LocalFocusManager.current
        )
    }
}