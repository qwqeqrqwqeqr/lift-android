package com.gradation.lift.designsystem.component.topBar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiftTopBar(
    modifier: Modifier = Modifier,
    title: String?,
    onClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    TopAppBar(
        title = {
            title?.let { title ->
                Text(
                    text = title,
                    color = LiftTheme.colorScheme.no9,
                    style = LiftTheme.typography.no1
                )
            }
        },
        modifier = modifier.padding(
            top = LiftTheme.space.space40,
            end = LiftTheme.space.space20,
            start = LiftTheme.space.space20,
        ),
        navigationIcon = {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onClick
                    ),
                    painter = painterResource(LiftIcon.ArrowBack),
                    contentDescription = "Back",
                    tint = LiftTheme.colorScheme.no9
                )
                Spacer(modifier = modifier.width(LiftTheme.space.space28))
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