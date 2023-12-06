package com.gradation.lift.designsystem.component.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import com.gradation.lift.designsystem.theme.LiftTheme


/**
 * 해당 스타일중 하나를 골라서 사용할 것
 * @since 2023-11-25 16:20:57
 */
enum class LiftTextStyle { No1, No2, No3, No4, No5, No6, No7, No8 }


/**
 * @param text 문자
 * @param color 해당 문자 전용 컬러 (기본 스타일 및 컬러로 사용할 경우 null 지정할 것)
 * @param style 해당 문자 전용 스타일 (기본 스타일 및 컬러로 사용할 경우 null 지정할 것)
 * @since 2023-11-25 16:16:24
 */
data class TextWithStyle(
    val text: String,
    val color: Color? = null,
    val style: LiftTextStyle? = null,
)

@Composable
internal fun LiftTextStyle.toStyle(): TextStyle = when (this) {
    LiftTextStyle.No1 -> LiftTheme.typography.no1
    LiftTextStyle.No2 -> LiftTheme.typography.no2
    LiftTextStyle.No3 -> LiftTheme.typography.no3
    LiftTextStyle.No4 -> LiftTheme.typography.no4
    LiftTextStyle.No5 -> LiftTheme.typography.no5
    LiftTextStyle.No6 -> LiftTheme.typography.no6
    LiftTextStyle.No7 -> LiftTheme.typography.no7
    LiftTextStyle.No8 -> LiftTheme.typography.no8
}


@Composable
internal fun LiftTextStyle.toSpanStyle(): SpanStyle = when (this) {
    LiftTextStyle.No1 -> LiftTheme.typography.no1.toSpanStyle()
    LiftTextStyle.No2 -> LiftTheme.typography.no2.toSpanStyle()
    LiftTextStyle.No3 -> LiftTheme.typography.no3.toSpanStyle()
    LiftTextStyle.No4 -> LiftTheme.typography.no4.toSpanStyle()
    LiftTextStyle.No5 -> LiftTheme.typography.no5.toSpanStyle()
    LiftTextStyle.No6 -> LiftTheme.typography.no6.toSpanStyle()
    LiftTextStyle.No7 -> LiftTheme.typography.no7.toSpanStyle()
    LiftTextStyle.No8 -> LiftTheme.typography.no8.toSpanStyle()
}

