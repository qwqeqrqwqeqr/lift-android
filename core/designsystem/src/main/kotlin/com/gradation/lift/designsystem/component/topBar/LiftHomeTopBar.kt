package com.gradation.lift.designsystem.component.topBar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.theme.LiftTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiftHomeTopBar(
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit,
) {
    TopAppBar(
        modifier = modifier
            .statusBarsPadding()
            .padding(end = LiftTheme.space.space8),
        title = {
            Icon(
                painter = painterResource(R.drawable.logo_extension_kor),
                contentDescription = "logo",
                tint = Color.Unspecified,
                modifier = modifier.size(LiftTheme.space.space72)
            )
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LiftTheme.colorScheme.no31,
            scrolledContainerColor = LiftTheme.colorScheme.no5,
            navigationIconContentColor = LiftTheme.colorScheme.no9,
            titleContentColor = LiftTheme.colorScheme.no9,
            actionIconContentColor = LiftTheme.colorScheme.no9,
        ),
        actions = actions
    )
}