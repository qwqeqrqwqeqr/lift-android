package com.gradation.lift.designsystem.component.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

enum class SnackBarCategory { Warn, Info }

@Composable
fun LiftSnackBar(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    snackbarCategory: SnackBarCategory = SnackBarCategory.Warn,
    onClick: (() -> Unit)? = null,
) {
    SnackbarHost(
        modifier = modifier.padding(LiftTheme.space.space12),
        hostState = snackbarHostState,
        snackbar = { data ->
            SnackbarComponent(
                modifier = modifier,
                message = data.visuals.message,
                actionLabel = data.visuals.actionLabel,
                snackbarCategory = snackbarCategory,
                onClick = onClick ?: { data.dismiss() },
            )
        }
    )
}



@Composable
fun SnackbarComponent(
    modifier: Modifier = Modifier,
    message: String,
    actionLabel: String?,
    snackbarCategory: SnackBarCategory = SnackBarCategory.Warn,
    onClick: () -> Unit,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(LiftTheme.space.space52),
        shape = RoundedCornerShape(LiftTheme.space.space8),
        colors = CardDefaults.cardColors(
            containerColor = LiftTheme.colorScheme.no9,
            contentColor = LiftTheme.colorScheme.no1
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
            ) {
                Icon(
                    painter = painterResource(LiftIcon.Warn),
                    contentDescription = "Info",
                    tint = when (snackbarCategory) {
                        SnackBarCategory.Warn -> LiftTheme.colorScheme.no12
                        SnackBarCategory.Info -> LiftTheme.colorScheme.no4
                    }
                )
                LiftText(
                    textStyle = LiftTextStyle.No5,
                    text = message,
                    color = LiftTheme.colorScheme.no21,
                    textAlign = TextAlign.Start
                )
            }
            Row(
                modifier = modifier
                    .background(
                        LiftTheme.colorScheme.no2,
                        RoundedCornerShape(LiftTheme.space.space6)
                    )
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource,
                        onClick = onClick,
                    )
                    .padding(
                        horizontal = LiftTheme.space.space8,
                        vertical = LiftTheme.space.space4
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                LiftText(
                    textStyle = LiftTextStyle.No5,
                    text = actionLabel ?: "확인",
                    color = LiftTheme.colorScheme.no5,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}