package com.gradation.lift.feature.badge.setting

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.setting.component.fail.FailBadgeListView
import com.gradation.lift.feature.badge.setting.component.fail.FailMainBadgeView
import com.gradation.lift.feature.badge.setting.component.loading.LoadingBadgeListView
import com.gradation.lift.feature.badge.setting.component.loading.LoadingMainBadgeView
import com.gradation.lift.feature.badge.setting.component.success.ApplyView
import com.gradation.lift.feature.badge.setting.component.success.BadgeListView
import com.gradation.lift.feature.badge.setting.component.success.MainBadgeView
import com.gradation.lift.feature.badge.setting.data.BadgeSettingViewModel
import com.gradation.lift.feature.badge.setting.data.state.BadgeUiState
import com.gradation.lift.model.model.badge.UserBadge

@Composable
fun BadgeSettingRoute(
    modifier: Modifier = Modifier,
    navigateBadgeGraphToPreGraph: () -> Unit,
    viewModel: BadgeSettingViewModel = hiltViewModel(),
) {

    val badgeUiState: BadgeUiState by viewModel.badgeUiState.collectAsStateWithLifecycle()

    val mainBadgeSet: Set<UserBadge> by viewModel.mainBadgeSet.collectAsStateWithLifecycle()
    val buttonCondition: Boolean by viewModel.buttonCondition.collectAsStateWithLifecycle()
    val snackBarState: Boolean by viewModel.snackBarState.collectAsStateWithLifecycle()

    val appendBadgeInMain: (UserBadge) -> Unit = viewModel.appendBadgeInMain()
    val removeBadgeInMain: (UserBadge) -> Unit = viewModel.removeBadgeInMain()
    val updateUserBadgeMainFlag: () -> Unit = viewModel.updateUserBadgeMainFlag()
    val updateSnackBarState: (Boolean) -> Unit = viewModel.updateSnackBarState()

    val snackbarHostState: SnackbarHostState by remember { mutableStateOf(SnackbarHostState()) }


    if (snackBarState) {
        LaunchedEffect(true) {
            snackbarHostState.showSnackbar(
                message = "대표뱃지 설정은 최대 5개까지 가능해요",
                duration = SnackbarDuration.Indefinite
            )
            updateSnackBarState(false)
        }
    }

    BadgeSettingScreen(
        modifier,
        badgeUiState,
        mainBadgeSet,
        buttonCondition,
        appendBadgeInMain,
        removeBadgeInMain,
        updateUserBadgeMainFlag,
        navigateBadgeGraphToPreGraph,
        snackbarHostState
    )

    BackHandler { navigateBadgeGraphToPreGraph() }
}

@Composable
fun BadgeSettingScreen(
    modifier: Modifier = Modifier,
    badgeUiState: BadgeUiState,
    mainBadgeSet: Set<UserBadge>,
    buttonCondition: Boolean,
    appendBadgeInMain: (UserBadge) -> Unit,
    removeBadgeInMain: (UserBadge) -> Unit,
    updateUserBadgeMainFlag: () -> Unit,
    navigateBadgeGraphToPreGraph: () -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "대표뱃지 설정",
                onBackClickTopBar = navigateBadgeGraphToPreGraph,
            )
        },
        snackbarHost = {
            LiftSnackBar(
                modifier = modifier,
                snackbarHostState = snackbarHostState
            )
        }
    ) { padding ->
        Surface(
            modifier = modifier
                .padding(padding)
                .fillMaxSize(),
            color = LiftTheme.colorScheme.no17,
        ) {
            when (badgeUiState) {
                is BadgeUiState.Fail -> {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        FailMainBadgeView(modifier)
                        FailBadgeListView(modifier)
                    }
                }

                BadgeUiState.Loading -> {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        LoadingMainBadgeView(modifier)
                        LoadingBadgeListView(modifier)
                    }
                }

                is BadgeUiState.Success -> {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        MainBadgeView(modifier, mainBadgeSet, removeBadgeInMain)
                        Column(modifier = modifier.weight(1f)) {
                            BadgeListView(
                                modifier,
                                badgeUiState.badgeList,
                                mainBadgeSet,
                                appendBadgeInMain
                            )
                        }
                        ApplyView(modifier, buttonCondition, updateUserBadgeMainFlag)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BadgeSettingScreenPreview(){
    MaterialTheme {
        BadgeSettingScreen(
            badgeUiState = BadgeUiState.Fail(""),
            mainBadgeSet = emptySet() ,
            buttonCondition = false,
            appendBadgeInMain = {},
            removeBadgeInMain = {},
            updateUserBadgeMainFlag = {  },
            navigateBadgeGraphToPreGraph = {  },
            snackbarHostState =  SnackbarHostState()
        )
    }
}
