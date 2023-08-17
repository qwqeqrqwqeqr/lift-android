package com.gradation.lift.feature.register_detail.gender

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.*
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.register_detail.gender.component.ProgressNumberView
import com.gradation.lift.feature.register_detail.name.data.RegisterDetailSharedViewModel
import com.gradation.lift.model.model.user.Gender
import com.gradation.lift.navigation.Router
import com.gradation.lift.ui.utils.DevicePreview

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun RegisterDetailGenderRoute(
    navController: NavController,
    navigateGenderToHeightWeight: () -> Unit,
    navigateGenderToName: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterDetailGenderViewModel = hiltViewModel(),
) {
    val registerBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(Router.REGISTER_DETAIL_GRAPH_NAME) }
    val sharedViewModel: RegisterDetailSharedViewModel = hiltViewModel(registerBackStackEntry)


    val gender: Gender by viewModel.gender.collectAsStateWithLifecycle()
    val name: String by sharedViewModel.createUserDetailName.collectAsStateWithLifecycle()

    val currentRegisterProgressNumber: Int by sharedViewModel.currentRegisterProgressNumber.collectAsStateWithLifecycle()
    val totalRegisterProgressNumber: Int by sharedViewModel.totalRegisterProgressNumber.collectAsStateWithLifecycle()

    val updateMale: () -> Unit = viewModel.updateMale()
    val updateFemale: () -> Unit = viewModel.updateFemale()
    val updateCreateUserDetailGender: (Gender) -> Unit =
        sharedViewModel.updateCreateUserDetailGender()
    val updateCurrentRegisterProgressNumber: (Int) -> Unit =
        sharedViewModel.updateCurrentRegisterProgressNumber()

    RegisterDetailGenderScreen(
        modifier,
        gender,
        name,
        currentRegisterProgressNumber,
        totalRegisterProgressNumber,
        updateMale,
        updateFemale,
        updateCreateUserDetailGender,
        updateCurrentRegisterProgressNumber,
        navigateGenderToHeightWeight,
        navigateGenderToName
    )


    BackHandler(onBack = { navigateGenderToName() })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RegisterDetailGenderScreen(
    modifier: Modifier = Modifier,
    gender: Gender,
    name: String,
    currentRegisterProgressNumber: Int,
    totalRegisterProgressNumber: Int,
    updateMale: () -> Unit,
    updateFemale: () -> Unit,
    updateCreateUserDetailGender: (Gender) -> Unit,
    updateCurrentRegisterProgressNumber: (Int) -> Unit,
    navigateGenderToHeightWeight: () -> Unit,
    navigateGenderToName: () -> Unit,
) {

    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "추가정보 입력",
                onBackClickTopBar = navigateGenderToName
            )
        },
    ) { padding ->
        Surface(
            color = LiftTheme.colorScheme.no5,
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .padding(16.dp)
            ) {
                ProgressNumberView(
                    modifier,
                    currentRegisterProgressNumber,
                    totalRegisterProgressNumber
                )
                Spacer(modifier = modifier.padding(14.dp))
                Text(
                    text = buildAnnotatedString {
                        append("${name}님의 ")
                        withStyle(
                            style = SpanStyle(color = LiftTheme.colorScheme.no4),
                        ) {
                            append("성별")
                        }
                        append("은 무엇인가요?")
                    },
                    style = LiftTheme.typography.no1,
                    color = LiftTheme.colorScheme.no11,
                )
                Spacer(modifier = modifier.padding(15.dp))
                Row {
                    LiftToggleTextBox(
                        text = "남자", checked = gender.getGenderValue() == Gender.MALE_VALUE,
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        onCheckedChange = { updateMale() }
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    LiftToggleTextBox(
                        text = "여자",
                        checked = gender.getGenderValue() == Gender.FEMALE_VALUE,
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        onCheckedChange = { updateFemale() }
                    )
                }
                Spacer(modifier = modifier.padding(18.dp))
                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = {
                        updateCreateUserDetailGender(gender)
                        updateCurrentRegisterProgressNumber(currentRegisterProgressNumber+1)
                        navigateGenderToHeightWeight()
                    },
                ) {
                    Text(
                        text = "다음",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }
            }

        }

    }
}


@SuppressLint("UnrememberedMutableState")
@DevicePreview
@Composable
fun RegisterDetailGenderScreenPreview(
    modifier: Modifier = Modifier,
) {
    LiftMaterialTheme {
        RegisterDetailGenderScreen(
            modifier = modifier,
            gender = Gender.Male(),
            name = "리프트",
            currentRegisterProgressNumber = 2,
            totalRegisterProgressNumber = 4,
            updateMale = {},
            updateFemale = {},
            updateCreateUserDetailGender = {},
            updateCurrentRegisterProgressNumber = {},
            navigateGenderToHeightWeight = {},
            navigateGenderToName = {}
        )
    }
}