package com.gradation.lift.feature.badge.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftIconButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.component.BadgeInfoView

@Composable
fun BadgeBadgeRoute(
    modifier: Modifier = Modifier,
    navigateBadgeGraphToPreGraph: () -> Unit,
    navigateBadgeToSettingInBadgeGraph: () -> Unit,
    viewModel: BadgeBadgeViewModel = hiltViewModel(),
) {


    BadgeBadgeScreen(
        modifier,

        )

}

@Composable
fun BadgeBadgeScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "내 뱃지",
                onBackClickTopBar = {},
                actions = {
                    LiftIconButton(
                        onClick = { },
                        modifier = modifier
                    ) {
                        Icon(
                            painter = painterResource(LiftIcon.BadgeAdd),
                            contentDescription = "",
                            tint = Color.Unspecified,
                        )
                    }
                }
            )
        },
    ) { padding ->
        Surface(
            modifier = modifier
                .padding(padding)
                .fillMaxSize(),
            color = LiftTheme.colorScheme.no17,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                BadgeInfoView(modifier)

                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(
                            LiftTheme.colorScheme.no5,
                            RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                        )
                ) {
                    Column(modifier = modifier.padding(16.dp)) {
                        Text(
                            text = "뱃지보관함",
                            color = LiftTheme.colorScheme.no9,
                            style = LiftTheme.typography.no3
                        )
                    }

                    Surface(
                        color = LiftTheme.colorScheme.no17,
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.padding(horizontal = 12.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = buildAnnotatedString {
                                    append("총 ")
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight(700)
                                        ),
                                    ) {
                                        append("개")
                                    }
                                    append("의 뱃지")
                                },
                                style = LiftTheme.typography.no6,
                                color = LiftTheme.colorScheme.no9,
                            )
                            Icon(
                                modifier = modifier
                                    .width(16.dp)
                                    .height(18.dp),
                                painter = painterResource(id = LiftIcon.Filter),
                                contentDescription = "filter",
                                tint = Color.Unspecified
                            )
                        }
                    }
                    Column(
                        modifier = modifier.height(256.dp)
                    ){}
                }
            }

        }
    }
}

@Composable
@Preview
fun BadgeBadgeScreenPreview() {
    LiftMaterialTheme {
        BadgeBadgeScreen()
    }
}
