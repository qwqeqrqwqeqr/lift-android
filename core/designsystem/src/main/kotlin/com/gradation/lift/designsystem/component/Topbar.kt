package com.gradation.lift.designsystem.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiftBackTopBar(
    title: String?,
    onBackClickTopBar: () -> Unit,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = {
            title?.let{ title->
                Text(
                    text = title,
                    color = LiftTheme.colorScheme.no9,
                    style = LiftTheme.typography.no1
                )
            }
        },
        modifier = modifier.statusBarsPadding(),
        navigationIcon = {
            LiftIconButton(
                onClick = onBackClickTopBar,
                modifier = modifier
            ) {
                Icon(
                    painter = painterResource(LiftIcon.ArrowBack),
                    contentDescription = "",
                    tint = Color.Unspecified,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LiftTheme.colorScheme.no5,
            scrolledContainerColor = LiftTheme.colorScheme.no5,
            navigationIconContentColor = LiftTheme.colorScheme.no9,
            titleContentColor = LiftTheme.colorScheme.no9,
            actionIconContentColor = LiftTheme.colorScheme.no9,
        ),
        actions = actions
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiftCloseTopBar(
    title: String?,
    onCloseClickTopBar: () -> Unit,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = {
            title?.let{ title->
                Text(
                    text = title,
                    color = LiftTheme.colorScheme.no9,
                    style = LiftTheme.typography.no1
                )
            }
        },
        modifier = modifier.statusBarsPadding(),
        navigationIcon = {
            LiftIconButton(
                onClick = onCloseClickTopBar,
                modifier = modifier
            ) {
                Icon(
                    painter = painterResource(LiftIcon.Close),
                    contentDescription = "",
                    tint = Color.Unspecified,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LiftTheme.colorScheme.no5,
            scrolledContainerColor = LiftTheme.colorScheme.no5,
            navigationIconContentColor = LiftTheme.colorScheme.no9,
            titleContentColor = LiftTheme.colorScheme.no9,
            actionIconContentColor = LiftTheme.colorScheme.no9,
        ),
        actions = actions
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiftTitleTopBar(
    title: String,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = LiftTheme.colorScheme.no9,
                style = LiftTheme.typography.no1
            )
        },
        modifier = modifier.statusBarsPadding(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LiftTheme.colorScheme.no5,
            scrolledContainerColor = LiftTheme.colorScheme.no5,
            navigationIconContentColor = LiftTheme.colorScheme.no9,
            titleContentColor = LiftTheme.colorScheme.no9,
            actionIconContentColor = LiftTheme.colorScheme.no9,
        ),
        actions = actions
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiftHomeTopBar(
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    TopAppBar(
        modifier = modifier.statusBarsPadding().padding(end = 8.dp),
        title={
            Icon(
                painter = painterResource(R.drawable.logo_extension_kor),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = modifier.size(72.dp)
            )
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LiftTheme.colorScheme.no31,
            scrolledContainerColor = LiftTheme.colorScheme.no5,
            navigationIconContentColor = LiftTheme.colorScheme.no9,
            titleContentColor = LiftTheme.colorScheme.no9,
            actionIconContentColor = LiftTheme.colorScheme.no9,
        ),
        actions = actions,
        scrollBehavior=scrollBehavior
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LiftBackTopBarPreview() {
    LiftMaterialTheme {
        Column() {
            LiftBackTopBar(
                title = "리프트",
                onBackClickTopBar = {}
            )
            Spacer(modifier = Modifier.padding(48.dp))
            LiftCloseTopBar(
                title = "리프트",
                onCloseClickTopBar = {}
            )
            Spacer(modifier = Modifier.padding(48.dp))
            LiftTitleTopBar(
                title = "리프트",
            )

            Spacer(modifier = Modifier.padding(48.dp))
            LiftHomeTopBar()

        }
    }
}
