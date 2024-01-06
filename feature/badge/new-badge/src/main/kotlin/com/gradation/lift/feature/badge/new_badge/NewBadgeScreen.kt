package com.gradation.lift.feature.badge.new_badge

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.new_badge.data.CreateUserBadgeState
import com.gradation.lift.feature.badge.new_badge.data.NewBadgeViewModel
import com.gradation.lift.feature.home.data.HomeViewModel
import com.gradation.lift.feature.home.data.state.BadgeConditionState
import com.gradation.lift.model.model.badge.Badge
import com.gradation.lift.navigation.Route

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun NewBadgeRoute(
    modifier: Modifier = Modifier,
    navigateNewBadgeGraphToHomeGraph: () -> Unit,
    navController: NavController,
    viewModel: NewBadgeViewModel = hiltViewModel(),
) {
    val homeBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(Route.HOME_GRAPH_NAME) }
    val sharedViewModel: HomeViewModel = hiltViewModel(homeBackStackEntry)

    val badgeConditionState: BadgeConditionState by sharedViewModel.badgeConditionState.collectAsStateWithLifecycle()
    val createUserBadgeState: CreateUserBadgeState by viewModel.createUserBadgeState.collectAsStateWithLifecycle()

    val createUserBadge: (Int) -> Unit = viewModel.createUserBadge()

    when (createUserBadgeState) {
        is CreateUserBadgeState.Fail -> {}
        CreateUserBadgeState.None -> {}
        CreateUserBadgeState.Success -> {
            LaunchedEffect(true) {
                navigateNewBadgeGraphToHomeGraph()
            }
        }
    }

    when (val state = badgeConditionState) {
        BadgeConditionState.None -> {}
        is BadgeConditionState.Success -> {
            NewBadgeScreen(
                modifier = modifier, badge = state.badge, createUserBadge = createUserBadge
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewBadgeScreen(
    modifier: Modifier = Modifier, badge: Badge, createUserBadge: (Int) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LiftTheme.colorScheme.no30)
            .padding(horizontal = 32.dp)
    ) {


        Column(
            modifier
                .clip(RoundedCornerShape(24.dp))
                .background(
                    color = LiftTheme.colorScheme.no5, shape = RoundedCornerShape(size = 24.dp)
                )
                .align(Alignment.Center)
                .padding(top = 16.dp, bottom = 24.dp)
                .zIndex(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.End,
            ) {
                IconButton(onClick = { createUserBadge(badge.id) }) {
                    Icon(
                        painter = painterResource(LiftIcon.Close),
                        contentDescription = "close",
                        tint = Color.Unspecified,
                    )
                }
            }
            BoxWithConstraints(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(R.drawable.halo),
                    contentDescription = "halo",
                    modifier = modifier.fillMaxWidth()
                )
                GlideImage(
                    model = badge.url,
                    modifier = modifier.width(maxWidth / 2),
                    contentDescription = "badge"
                )
            }

            Column(
                modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    badge.name,
                    textAlign = TextAlign.Center,
                    style = LiftTheme.typography.no2,
                    color = LiftTheme.colorScheme.no3
                )
                Text(
                    badge.description,
                    textAlign = TextAlign.Center,
                    style = LiftTheme.typography.no4,
                    color = LiftTheme.colorScheme.no9
                )
                Spacer(modifier = modifier.padding(16.dp))

                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = { createUserBadge(badge.id) },
                ) {
                    Text(
                        text = "확인",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }
            }
        }
        Image(
            modifier = modifier
                .fillMaxSize()
                .scale(1.5f)
                .zIndex(1f),
            painter = painterResource(id = R.drawable.badge_halo),
            contentDescription = "badgeHalo"
        )
        Image(
            modifier = modifier
                .fillMaxSize()
                .zIndex(3f),
            painter = painterResource(id = R.drawable.badge_congrats),
            contentDescription = "badgeCongrats"
        )

    }
}

@Preview
@Composable
fun NewBadgePreview() {
    LiftMaterialTheme {
        NewBadgeScreen(modifier = Modifier, badge = Badge(
            0,
            "초심자",
            "설명",
            "힌트",
            "",
            ""
        ), createUserBadge = {})
    }
}