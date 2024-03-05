package com.gradation.lift.network.dto.work

import com.gradation.lift.model.model.work.EffectContent
import com.gradation.lift.model.model.work.SequenceContent
import com.gradation.lift.model.model.work.WorkCategory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPopularWorkCategoryResponseDto(
    @SerialName("work_category")
    val workCategory: List<WorkCategoryDto>,
) {
    fun toDomain(): List<WorkCategory> =
        this.workCategory.map {
            WorkCategory(
                id = it.id,
                name = it.name,
                equipment = it.equipment,
                searchTag = it.searchTag,
                workPart = it.workPart,
                introduce = it.introduce,
                description = it.description,
                sequence = it.sequence.mapIndexed { index, item ->
                    SequenceContent(
                        sequence = index + 1,
                        title = item.title,
                        content = item.content,
                    )
                },
                effect = it.effect.map { EffectContent(title = it.title, content = it.content) },
                caution = it.caution
            )
        }
}
