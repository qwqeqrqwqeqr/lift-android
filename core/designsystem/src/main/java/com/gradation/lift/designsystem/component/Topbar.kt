package com.gradation.lift.designsystem.component

import android.inputmethodservice.Keyboard.Row
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiftBackTopBar(
    title: String,
    onBackClick: () -> Unit,
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
        navigationIcon = {
            LiftIconButton(
                onClick = onBackClick,
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
        modifier = modifier.statusBarsPadding(),
        title={
            Icon(
                painter = painterResource(R.drawable.logo_extension),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = modifier.size(72.dp)
            )
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LiftTheme.colorScheme.no5,
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
                onBackClick = {}
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

