package com.gradation.lift.designsystem.component.textField

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

/**
 * [LiftDropDownTextField]
 * [isSelected] 값 선택 여부
 * [emptyText] 아무것도 선택되지 않았을 때 나오게 되는 텍스트,
 * [selectedText] 선택된 텍스트
 * @since 2024-02-19 18:39:39
 */
@Composable
fun LiftDropDownTextField(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    emptyText: String,
    selectedText: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(LiftTheme.space.space48)
            .background(
                color = LiftTheme.colorScheme.no1,
                shape = RoundedCornerShape(size = LiftTheme.space.space12)
            )
            .padding(
                start = LiftTheme.space.space12,
                end = LiftTheme.space.space24,
                top = LiftTheme.space.space6,
                bottom = LiftTheme.space.space6
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
    ) {
        LiftText(
            modifier = modifier.weight(1f),
            textStyle = LiftTextStyle.No6,
            text = if (isSelected) selectedText else emptyText,
            color = LiftTheme.colorScheme.no9,
            textAlign = TextAlign.Start,
            maxLines = 1
        )
        LiftIconBox(
            icon = LiftIcon.ChevronDown,
            iconType = IconType.Vector,
            iconBoxSize = IconBoxSize.Size16,
            tint = LiftTheme.colorScheme.no6
        )
    }
}


@Composable
@Preview
fun LiftDropDownTextFieldPreview(
    modifier: Modifier = Modifier,
) {
    LiftMaterialTheme {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no4)
        ) {
            LiftDropDownTextField(
                modifier,
                false,
                "목록을 선택해주세요",
                "메뉴1",
            )

        }
    }
}