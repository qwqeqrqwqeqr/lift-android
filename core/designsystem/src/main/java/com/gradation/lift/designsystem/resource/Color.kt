package com.gradation.lift.designsystem.resource

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


/**
 * [NO1] :인풋배경, 아이콘 버튼 배경
 * [NO2] :인풋텍스트
 * [NO3] :인풋라벨
 * [NO4] :Primary (활성화버튼,탭,시간초,인풋버튼)
 * [NO5] :카드, 백그라운드
 * [NO6] :아이콘(비활성화)
 * [NO7] :캡션/ 작은텍스트 버튼
 * [NO8] :라인
 * [NO9] :상단 해더 / 텍스트 입력시 / 팝업 본문
 * [NO10] :탭 (비활성화),
 * [NO11] :약관, 탭(활성화)
 * [NO12] :오류
 * [NO13] :버튼 (비활성화)
 * [NO14] :토스트배경
 * [NO15] :
 * [NO16] :
 * [NO17] :홈 백그라운드
 * [NO18] :일러스트 컬러
 * [NO19] :일러스트 컬러
 * [NO20] :일러스트 컬러
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
    )

fun liftDarkColorScheme(
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
    )

val LocalLiftColorScheme = staticCompositionLocalOf { liftLightColorScheme() }
