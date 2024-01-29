package com.gradation.lift.feature.work.findWorkCategory.data.model

import com.gradation.lift.model.model.work.WorkCategory

/**
 * [TagWorkCategory]
 *  태그가 있는 운동 카테고리 모델
 *  @since 2023-12-07 11:32:37
 */
data class TagWorkCategory(
    val workCategory: WorkCategory,
    val popularTag: Boolean,
    val recommendTag: Boolean
)
