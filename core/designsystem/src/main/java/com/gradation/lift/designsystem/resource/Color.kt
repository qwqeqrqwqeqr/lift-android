package com.gradation.lift.designsystem.resource

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


/**
 * [COLOR_NO1] :인풋배경, 아이콘 버튼 배경
 * [COLOR_NO2] :인풋텍스트
 * [COLOR_NO3] :인풋라벨
 * [COLOR_NO4] :Primary (활성화버튼,탭,시간초,인풋버튼)
 * [COLOR_NO5] :카드, 백그라운드
 * [COLOR_NO6] :아이콘(비활성화)
 * [COLOR_NO7] :캡션/ 작은텍스트 버튼
 * [COLOR_NO8] :라인
 * [COLOR_NO9] :상단 해더 / 텍스트 입력시 / 팝업 본문
 * [COLOR_NO10] :탭 (비활성화),
 * [COLOR_NO11] :약관, 탭(활성화)
 * [COLOR_NO12] :오류
 * [COLOR_NO13] :버튼 (비활성화)
 * [COLOR_NO14] :토스트배경
 * [COLOR_NO15] :
 * [COLOR_NO16] :
 * [COLOR_NO17] :홈 백그라운드
 * [COLOR_NO18] :일러스트 컬러
 * [COLOR_NO19] :일러스트 컬러
 * [COLOR_NO20] :일러스트 컬러
 * [COLOR_NO21] :오류
 * [COLOR_NO22] :
 * [COLOR_NO23] :
 *
 */
internal val COLOR_NO1: Color = Color(0xFFF2F2F5)
internal val COLOR_NO2: Color = Color(0xFF87878B)
internal val COLOR_NO3: Color = Color(0xFF333333)
internal val COLOR_NO4: Color = Color(0xFF0080FF)
internal val COLOR_NO5: Color = Color(0xFFFFFFFF)
internal val COLOR_NO6: Color = Color(0xFFA4A4AC)
internal val COLOR_NO7: Color = Color(0xFF64627A)
internal val COLOR_NO8: Color = Color(0xFFCFCFD9)
internal val COLOR_NO9: Color = Color(0xFF404045)
internal val COLOR_NO10: Color = Color(0xFF96969D)
internal val COLOR_NO11: Color = Color(0xFF000000)
internal val COLOR_NO12: Color = Color(0xFFFF4F4F)
internal val COLOR_NO13: Color = Color(0xFFBFBFC7)
internal val COLOR_NO14: Color = Color(0xFF7A7A82)
internal val COLOR_NO15: Color = Color(0xFFE5F2FF)
internal val COLOR_NO16: Color = Color(0xFF00264C)
internal val COLOR_NO17: Color = Color(0xFFEEF1F6)
internal val COLOR_NO18: Color = Color(0xFF40E2BD)
internal val COLOR_NO19: Color = Color(0xFFFC5858)
internal val COLOR_NO20: Color = Color(0xFFFFC124)
internal val COLOR_NO21: Color = Color(0xFFFFEDED)
internal val COLOR_NO22: Color = Color(0xDDFF4F4F)
internal val COLOR_NO23: Color = Color(0xFFE3E3E9)
internal val COLOR_NO24: Color = Color(0xFF32A836)
internal val COLOR_NO25: Color = Color(0xFFE4FFE5)
internal val COLOR_NO26: Color = Color(0xFF66B3FF)
internal val COLOR_NO27: Color = Color(0xFF7AE27E)
internal val COLOR_NO28: Color = Color(0xFFFF8888)
internal val COLOR_NO29: Color = Color(0xFF94C9FF)


data class LiftColorScheme(
    val no1: Color,
    val no2: Color,
    val no3: Color,
    val no4: Color,
    val no5: Color,
    val no6: Color,
    val no7: Color,
    val no8: Color,
    val no9: Color,
    val no10: Color,
    val no11: Color,
    val no12: Color,
    val no13: Color,
    val no14: Color,
    val no15: Color,
    val no16: Color,
    val no17: Color,
    val no18: Color,
    val no19: Color,
    val no20: Color,
    val no21: Color,
    val no22: Color,
    val no23: Color,
    val no24: Color,
    val no25: Color,
    val no26: Color,
    val no27: Color,
    val no28: Color,
    val no29: Color,
)

fun liftLightColorScheme(
    no1: Color = COLOR_NO1,
    no2: Color = COLOR_NO2,
    no3: Color = COLOR_NO3,
    no4: Color = COLOR_NO4,
    no5: Color = COLOR_NO5,
    no6: Color = COLOR_NO6,
    no7: Color = COLOR_NO7,
    no8: Color = COLOR_NO8,
    no9: Color = COLOR_NO9,
    no10: Color = COLOR_NO10,
    no11: Color = COLOR_NO11,
    no12: Color = COLOR_NO12,
    no13: Color = COLOR_NO13,
    no14: Color = COLOR_NO14,
    no15: Color = COLOR_NO15,
    no16: Color = COLOR_NO16,
    no17: Color = COLOR_NO17,
    no18: Color = COLOR_NO18,
    no19: Color = COLOR_NO19,
    no20: Color = COLOR_NO20,
    no21: Color = COLOR_NO21,
    no22: Color = COLOR_NO22,
    no23: Color = COLOR_NO23,
    no24: Color = COLOR_NO24,
    no25: Color = COLOR_NO25,
    no26: Color = COLOR_NO26,
    no27: Color = COLOR_NO27,
    no28: Color = COLOR_NO28,
    no29: Color = COLOR_NO29,
): LiftColorScheme =
    LiftColorScheme(
        no1,
        no2,
        no3,
        no4,
        no5,
        no6,
        no7,
        no8,
        no9,
        no10,
        no11,
        no12,
        no13,
        no14,
        no15,
        no16,
        no17,
        no18,
        no19,
        no20,
        no21,
        no22,
        no23,
        no24,
        no25,
        no26,
        no27,
        no28,
        no29,
    )

fun liftDarkColorScheme(
    no1: Color = Color.Unspecified,
    no2: Color = Color.Unspecified,
    no3: Color = Color.Unspecified,
    no4: Color = Color.Unspecified,
    no5: Color = Color.Unspecified,
    no6: Color = Color.Unspecified,
    no7: Color = Color.Unspecified,
    no8: Color = Color.Unspecified,
    no9: Color = Color.Unspecified,
    no10: Color = Color.Unspecified,
    no11: Color = Color.Unspecified,
    no12: Color = Color.Unspecified,
    no13: Color = Color.Unspecified,
    no14: Color = Color.Unspecified,
    no15: Color = Color.Unspecified,
    no16: Color = Color.Unspecified,
    no17: Color = Color.Unspecified,
    no18: Color = Color.Unspecified,
    no19: Color = Color.Unspecified,
    no20: Color = Color.Unspecified,
    no21: Color = Color.Unspecified,
    no22: Color = Color.Unspecified,
    no23: Color = Color.Unspecified,
    no24: Color = Color.Unspecified,
    no25: Color = Color.Unspecified,
    no26: Color = Color.Unspecified,
    no27: Color = Color.Unspecified,
    no28: Color = Color.Unspecified,
    no29: Color = Color.Unspecified,
): LiftColorScheme =
    LiftColorScheme(
        no1,
        no2,
        no3,
        no4,
        no5,
        no6,
        no7,
        no8,
        no9,
        no10,
        no11,
        no12,
        no13,
        no14,
        no15,
        no16,
        no17,
        no18,
        no19,
        no20,
        no21,
        no22,
        no23,
        no24,
        no25,
        no26,
        no27,
        no28,
        no29,
    )

val LocalLiftColorScheme = staticCompositionLocalOf { liftLightColorScheme() }
