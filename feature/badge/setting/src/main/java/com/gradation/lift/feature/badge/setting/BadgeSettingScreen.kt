package com.gradation.lift.feature.badge.setting

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.setting.data.BadgeSettingViewModel
import com.gradation.lift.feature.badge.setting.data.state.BadgeUiState
import com.gradation.lift.model.model.badge.UserBadge

@Composable
fun BadgeSettingRoute(
    modifier: Modifier = Modifier,
    navigateSettingToBadgeInBadgeGraph: () -> Unit,
    viewModel: BadgeSettingViewModel = hiltViewModel(),
) {

    val badgeUiState: BadgeUiState by viewModel.badgeUiState.collectAsStateWithLifecycle()

    val mainBadgeSet: Set<UserBadge> by viewModel.mainBadgeSet.collectAsStateWithLifecycle()
    val buttonCondition: Boolean by viewModel.buttonCondition.collectAsStateWithLifecycle()

    val appendBadgeInMain: (UserBadge) -> Unit = viewModel.appendBadgeInMain()
    val removeBadgeInMain: (UserBadge) -> Unit = viewModel.removeBadgeInMain()
    val updateUserBadgeMainFlag: () -> Unit = viewModel.updateUserBadgeMainFlag()

    BadgeSettingScreen(
        modifier,
        badgeUiState,
        mainBadgeSet,
        buttonCondition,
        appendBadgeInMain,
        removeBadgeInMain,
        updateUserBadgeMainFlag,
        navigateSettingToBadgeInBadgeGraph
    )

    BackHandler { navigateSettingToBadgeInBadgeGraph() }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BadgeSettingScreen(
    modifier: Modifier = Modifier,
    badgeUiState: BadgeUiState,
    mainBadgeSet: Set<UserBadge>,
    buttonCondition: Boolean,
    appendBadgeInMain: (UserBadge) -> Unit,
    removeBadgeInMain: (UserBadge) -> Unit,
    updateUserBadgeMainFlag: () -> Unit,
    navigateSettingToBadgeInBadgeGraph: () -> Unit
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "대표뱃지 설정",
                onBackClickTopBar = navigateSettingToBadgeInBadgeGraph,
            )
        },
    ) { padding ->
        Surface(
            modifier = modifier
                .padding(padding)
                .fillMaxSize(),
            color = LiftTheme.colorScheme.no17,
        ) {
            when (badgeUiState) {
                is BadgeUiState.Fail -> {

                }

                BadgeUiState.Loading -> {

                }

                is BadgeUiState.Success -> {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {


                        Column(
                            modifier = modifier
                                .background(
                                    LiftTheme.colorScheme.no5,
                                    RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                                )
                                .padding(16.dp)
                        ) {
                            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                mainBadgeSet.forEach { badge ->
                                    Column(
                                        modifier = modifier.weight(1f),
                                        verticalArrangement = Arrangement.spacedBy(2.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Box(modifier = modifier.size(56.dp)) {
                                            GlideImage(
                                                modifier = modifier
                                                    .size(56.dp)
                                                    .align(Alignment.Center),
                                                model = badge.badge.url,
                                                contentDescription = "mainBadge"
                                            )
                                            IconButton(
                                                modifier = modifier
                                                    .offset(y = (-26).dp, x = 26.dp)
                                                    .align(Alignment.Center),
                                                onClick = { removeBadgeInMain(badge) }
                                            ) {
                                                Icon(
                                                    painter = painterResource(LiftIcon.Cancel),
                                                    contentDescription = "",
                                                    tint = Color.Unspecified,
                                                )
                                            }
                                        }
                                        Text(
                                            text = badge.badge.name,
                                            color = LiftTheme.colorScheme.no9,
                                            style = LiftTheme.typography.no6,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                                repeat(5 - mainBadgeSet.size) {
                                    Column(
                                        modifier = modifier
                                            .weight(1f)
                                    ) {
                                        Spacer(
                                            modifier = modifier
                                                .size(56.dp)
                                                .clip(CircleShape)
                                                .background(LiftTheme.colorScheme.no1)
                                        )
                                    }
                                }
                            }
                        }

                        Column(
                            modifier = modifier.weight(1f)
                                .background(
                                    LiftTheme.colorScheme.no5,
                                    RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                                )
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "획득한 뱃지",
                                color = LiftTheme.colorScheme.no9,
                                style = LiftTheme.typography.no3
                            )
                            LazyVerticalGrid(
                                modifier = modifier.padding(16.dp),
                                columns = GridCells.Fixed(4),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                            ) {
                                items(badgeUiState.badgeList) { badge ->
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(
                                            2.dp,
                                            Alignment.CenterVertically
                                        ),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Box(modifier = modifier.size(72.dp)) {
                                            GlideImage(
                                                modifier = modifier
                                                    .size(72.dp)
                                                    .noRippleClickable {
                                                        if (mainBadgeSet.count { it.badge.id == badge.badge.id } == 0) {
                                                            appendBadgeInMain(badge)
                                                        }
                                                    },
                                                model = badge.badge.url,
                                                contentDescription = "acquiredBadge"
                                            )
                                            if (mainBadgeSet.count{it.badge.id== badge.badge.id}==1) {
                                                Icon(
                                                    modifier = modifier
                                                        .size(24.dp)
                                                        .offset(y = (-24).dp, x = 24.dp)
                                                        .align(Alignment.Center),
                                                    painter = painterResource(LiftIcon.CheckBoxChecked),
                                                    contentDescription = "",
                                                    tint = Color.Unspecified
                                                )
                                            }
                                        }
                                        Text(
                                            text = badge.badge.name,
                                            color = LiftTheme.colorScheme.no9,
                                            style = LiftTheme.typography.no4,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = modifier
                                .background(LiftTheme.colorScheme.no5)
                                .padding(16.dp)
                        ) {
                            LiftButton(
                                modifier = modifier.fillMaxWidth(),
                                onClick = updateUserBadgeMainFlag,
                                enabled = buttonCondition
                            ) {
                                Text(
                                    text = "적용하기",
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
}

