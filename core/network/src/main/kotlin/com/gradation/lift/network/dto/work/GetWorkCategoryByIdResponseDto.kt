package com.gradation.lift.network.dto.work

import com.gradation.lift.model.model.work.EffectContent
import com.gradation.lift.model.model.work.SequenceContent
import com.gradation.lift.model.model.work.WorkCategory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWorkCategoryByIdResponseDto(
    @SerialName("work_category")
    val workCategory: WorkCategoryDto
) {
    fun toDomain(): WorkCategory =
        WorkCategory(
            id = workCategory.id,
            name = workCategory.name,
            equipment = workCategory.equipment,
            searchTag = workCategory.searchTag,
            workPart = workCategory.workPart,
            introduce = workCategory.introduce,
            description = workCategory.description,
            sequence = workCategory.sequence.mapIndexed { index, item ->
                SequenceContent(
                    sequence = index + 1,
                    title = item.title,
                    content = item.content,
                )
            },
            effect = workCategory.effect.map {
                EffectContent(
                    title = it.title,
                    content = it.content
                )
            },
            caution = workCategory.caution
        )
}
