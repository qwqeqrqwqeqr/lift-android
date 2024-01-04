package com.gradation.lift.feature.login.signIn.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun TitleView(
    modifier:Modifier= Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = LiftTheme.space.space40),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
    ) {
        Image(
            modifier = modifier.height(LiftTheme.space.space84),
            painter = painterResource(id = R.drawable.logo_v2),
            contentDescription = "LogoV2"
        )
        LiftMultiStyleText(
            modifier = modifier,
            defaultColor = LiftTheme.colorScheme.no3,
            defaultTextStyle = LiftTextStyle.No2,
            textWithStyleList = listOf(
                TextWithStyle(text = "매일매일 운동하고, 기록하고!\n"),
                TextWithStyle(text = "나만의 운동파트너, 리프트", color = LiftTheme.colorScheme.no4)
            ),
            textAlign = TextAlign.Center
        )
    }
}