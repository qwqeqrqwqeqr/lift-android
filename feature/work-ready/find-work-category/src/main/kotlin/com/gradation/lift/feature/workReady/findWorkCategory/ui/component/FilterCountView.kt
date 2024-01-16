package com.gradation.lift.feature.workReady.findWorkCategory.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.workReady.findWorkCategory.data.model.TagWorkCategory

@Composable
fun FilterCountView(
    modifier: Modifier = Modifier,
    workCategoryList: List<TagWorkCategory>,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = LiftTheme.space.space20),
    ) {
        LiftMultiStyleText(
            defaultColor = LiftTheme.colorScheme.no9,
            defaultTextStyle = LiftTextStyle.No6,
            textAlign = TextAlign.Center,
            textWithStyleList = listOf(
                TextWithStyle(text = "총 "),
                TextWithStyle(text = "${workCategoryList.size}개", style = LiftTextStyle.No5),
                TextWithStyle(text = "의 운동"),
            )
        )
    }
}