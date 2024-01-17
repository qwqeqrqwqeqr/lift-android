package com.gradation.lift.feature.analytics.analytics.data.model

/**
 * [WorkPartAnalyticsTargetType]
 * 운동 부위별 빈도수를 분석할 때, 어떤 타입을 분석할 것인지 결정해 주는 모델
 * [Pre] 지난 (월,주,년) 만 분석
 * [Current] 이번 (월,주,년) 만 분석
 * [All] 지난, 이번 모두 분석
 * @since 2024-01-10 19:33:59
 */
sealed class WorkPartAnalyticsTargetType{
    data object Pre :WorkPartAnalyticsTargetType()
    data object Current :WorkPartAnalyticsTargetType()
    data object All :WorkPartAnalyticsTargetType()
}
