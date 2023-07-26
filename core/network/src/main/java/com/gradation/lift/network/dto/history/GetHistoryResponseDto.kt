package com.gradation.lift.network.dto.history

import com.gradation.lift.model.history.History
import com.gradation.lift.model.history.HistoryRoutine
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.model.work.WorkSet
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime


@JsonClass(generateAdapter = true)
data class GetHistoryResponseDto(
    @Json(name = "history")
    val history: List<HistoryDto>,
) {
    fun toHistory(): List<History> = this.history.groupBy { it.historyId }.map {
        History(
            historyId = it.value.first().historyId,
            comment = it.value.first().comment,
            score = it.value.first().score,
            restTime = it.value.first().restTime,
            totalTime = it.value.first().totalTime,
            historyTimeStamp = it.value.first().historyTimeStamp,
            historyRoutine = it.value.map { history ->
                HistoryRoutine(
                    id = history.historyRoutine.historyRoutineId,
                    historyId = history.historyRoutine.historyId,
                    workCategory = WorkCategory(
                        id = history.historyRoutine.workCategory.id,
                        name = history.historyRoutine.workCategory.name,
                        workPart = WorkPart(
                            id = history.historyRoutine.workCategory.workPart.id,
                            name = history.historyRoutine.workCategory.workPart.name
                        ),
                        introduce = history.historyRoutine.workCategory.introduce,
                        description = history.historyRoutine.workCategory.description
                    ),
                    workSetList = history.historyRoutine.workWeightList.zip(history.historyRoutine.workRepetitionList)
                        .map { workSet ->
                            WorkSet(
                                weight = workSet.first,
                                repetition = workSet.second
                            )

                        }
                )
            }
        )
    }
}
