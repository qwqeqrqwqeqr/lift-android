package com.gradation.lift.feature.analytics.analytics.data.model

sealed class WorkPartAnalyticsTargetDate{
    data object Week :WorkPartAnalyticsTargetDate()
    data object Month :WorkPartAnalyticsTargetDate()
    data object Year :WorkPartAnalyticsTargetDate()
}
