package com.gradation.lift.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LiftErrorSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = modifier.shadow(0.dp),
                shape = RoundedCornerShape(24.dp),
                containerColor = LiftTheme.colorScheme.no22,
                contentColor = LiftTheme.colorScheme.no21
            ) {
                Row {
                    Icon(
                        painter = painterResource(LiftIcon.Warn),
                        contentDescription = "",
                        tint = Color.Unspecified,
                    )
                    Spacer(modifier = modifier.padding(6.dp))
                    Text(
                        text = data.visuals.message,
                        style = LiftTheme.typography.no5,
                        color = LiftTheme.colorScheme.no21,
                    )
                }
            }
        }

    )
}