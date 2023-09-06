package com.gradation.lift.feature.history.analytics.data.model

/**
 * [WorkPartAnalyticsTargetType]
 * 운동 부위별 빈도수를 분석할 때, 어떤 타입을 분석할 것인지 결정해 주는 모델
 * [Pre] 지난 (월,주,년) 만 분석
 * [Current] 이번 (월,주,년) 만 분석
 * [All] 지난, 이번 모두 분석
 * @since 2023-09-05 16:05:40
 */
sealed class WorkPartAnalyticsTargetType{
    object Pre :WorkPartAnalyticsTargetType()
    object Current :WorkPartAnalyticsTargetType()
    object All :WorkPartAnalyticsTargetType()
}