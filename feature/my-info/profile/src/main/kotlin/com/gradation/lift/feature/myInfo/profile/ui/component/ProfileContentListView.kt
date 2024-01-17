package com.gradation.lift.feature.myInfo.profile.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.myInfo.profile.data.model.ProfileContent
import com.gradation.lift.feature.myInfo.profile.data.state.ProfileState
import com.gradation.lift.ui.modifier.noRippleClickable

@Composable
fun ProfileContentListView(
    modifier: Modifier = Modifier,
    navigateProfileToUpdateNameInMyInfoGraph: (String) -> Unit,
    navigateProfileToUpdateInfoInMyInfoGraph: (String, Float, Float) -> Unit,
    profileState: ProfileState,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LiftTheme.colorScheme.no5)
    ) {
        listOf(
            ProfileContent(
                profileState.name,
                "닉네임 변경"
            ) { navigateProfileToUpdateNameInMyInfoGraph(profileState.name) },
            ProfileContent(
                "내 정보 수정",
                "키 몸무게 및 성별 변경"
            ) {
                navigateProfileToUpdateInfoInMyInfoGraph(
                    profileState.gender.getGenderValue(),
                    profileState.height,
                    profileState.weight
                )
            },
        ).forEach {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = LiftTheme.space.space16,
                        horizontal = LiftTheme.space.space20
                    )
                    .noRippleClickable { it.navigate() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = modifier,
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
                ) {

                    LiftText(
                        modifier = modifier,
                        textStyle = LiftTextStyle.No3,
                        text = it.title,
                        color = LiftTheme.colorScheme.no3,
                        textAlign = TextAlign.Start
                    )
                    it.content?.let {
                        LiftText(
                            modifier = modifier,
                            textStyle = LiftTextStyle.No6,
                            text = it,
                            color = LiftTheme.colorScheme.no2,
                            textAlign = TextAlign.Start
                        )
                    }
                }
                Icon(
                    modifier = modifier,
                    painter = painterResource(id = LiftIcon.ChevronRightLarge),
                    contentDescription = "chevronRightLarge",
                    tint = LiftTheme.colorScheme.no2
                )
            }
        }
    }
}