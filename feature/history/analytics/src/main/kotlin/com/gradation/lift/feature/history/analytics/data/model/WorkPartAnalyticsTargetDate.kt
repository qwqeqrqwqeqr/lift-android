package com.gradation.lift.feature.history.analytics.data.model

sealed class WorkPartAnalyticsTargetDate{
    object Week :WorkPartAnalyticsTargetDate()
    object Month :WorkPartAnalyticsTargetDate()
    object Year :WorkPartAnalyticsTargetDate()
}
