package com.gradation.lift.designsystem.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LiftErrorSnackBar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(
        modifier = modifier.padding(horizontal = 16.dp),
        hostState = snackbarHostState,
        snackbar = { data ->
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = cardColors(
                    containerColor = LiftTheme.colorScheme.no22,
                    contentColor = LiftTheme.colorScheme.no21
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = modifier
                        .fillMaxHeight()
                        .align(Alignment.Start)
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                ) {
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
                        modifier = modifier.align(Alignment.CenterVertically),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }

    )
}


@Composable
fun LiftInfoSnackBar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(
        modifier = modifier.padding(horizontal = 16.dp),
        hostState = snackbarHostState,
        snackbar = { data ->
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = cardColors(
                    containerColor = LiftTheme.colorScheme.no22,
                    contentColor = LiftTheme.colorScheme.no21
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .align(Alignment.Start)
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = data.visuals.message,
                        style = LiftTheme.typography.no5,
                        color = LiftTheme.colorScheme.no21,
                        modifier = modifier.align(Alignment.CenterVertically),
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = modifier.padding(6.dp))
                    IconButton(modifier=modifier.size(12.dp),onClick = {data.dismiss()}) {
                        Icon(
                            painter = painterResource(LiftIcon.Close),
                            contentDescription = "",
                            tint = LiftTheme.colorScheme.no21,
                        )
                    }

                }
            }
        }

    )
}


@Preview
@Composable
fun LiftSnackBarPreview(
    modifier: Modifier=Modifier
){
    LiftMaterialTheme {
        Column {


            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = cardColors(
                    containerColor = LiftTheme.colorScheme.no22,
                    contentColor = LiftTheme.colorScheme.no21
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = modifier
                        .fillMaxHeight()
                        .align(Alignment.Start)
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Icon(
                        painter = painterResource(LiftIcon.Warn),
                        contentDescription = "",
                        tint = Color.Unspecified,
                    )
                    Spacer(modifier = modifier.padding(6.dp))
                    Text(
                        text = "Routers Street 3126, Wurdong Heights,",
                        style = LiftTheme.typography.no5,
                        color = LiftTheme.colorScheme.no21,
                        modifier = modifier.align(Alignment.CenterVertically),
                        textAlign = TextAlign.Start
                    )
                }
            }
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = cardColors(
                    containerColor = LiftTheme.colorScheme.no14,
                    contentColor = LiftTheme.colorScheme.no21
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .align(Alignment.Start)
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Routers Street 3126, Wurdong Heights,",
                        style = LiftTheme.typography.no5,
                        color = LiftTheme.colorScheme.no21,
                        modifier = modifier.align(Alignment.CenterVertically),
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = modifier.padding(6.dp))
                    IconButton(modifier=modifier.size(12.dp),onClick = {}) {
                        Icon(
                            painter = painterResource(LiftIcon.Close),
                            contentDescription = "",
                            tint = LiftTheme.colorScheme.no21,
                        )
                    }

                }
            }
        }
    }
}