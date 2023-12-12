package com.gradation.lift.designsystem.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle


/**
 * 여러 스타일을 가지고 있는 텍스트
 * @since 2023-11-25 16:19:25
 */
@Composable
fun LiftMultiStyleText(
    modifier: Modifier = Modifier,
    defaultColor: Color,
    defaultTextStyle: LiftTextStyle,
    textWithStyleList: List<TextWithStyle> = emptyList(),
    textAlign: TextAlign,
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            textWithStyleList.forEach { textWithStyle ->
                withStyle(
                    style = (textWithStyle.style?.toSpanStyle() ?: defaultTextStyle.toSpanStyle())
                        .copy(
                            color = textWithStyle.color ?: defaultColor
                        )
                ) {
                    append(textWithStyle.text)
                }
            }
        },
        color = defaultColor,
        style = defaultTextStyle.toStyle(),
        textAlign = textAlign,
    )
}



