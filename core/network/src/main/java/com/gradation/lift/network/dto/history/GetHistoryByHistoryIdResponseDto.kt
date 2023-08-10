package com.gradation.lift.network.dto.history

import com.gradation.lift.model.model.history.History
import com.gradation.lift.model.model.history.HistoryRoutine
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart
import com.gradation.lift.model.model.work.WorkSet
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalTime.Companion.fromSecondOfDay
import kotlinx.datetime.LocalDateTime.Companion.parse

@JsonClass(generateAdapter = true)
data class GetHistoryByHistoryIdResponseDto(
    @Json(name = "history")
    val history: List<HistoryDto>
){
    fun toDomain(): List<History> = this.history.groupBy { it.historyId }.map {
        History(
            historyId = it.value.first().historyId,
            comment = it.value.first().comment,
            score = it.value.first().score,
            restTime = fromSecondOfDay(it.value.first().restTime),
            workTime = fromSecondOfDay(it.value.first().workTime),
            totalTime = fromSecondOfDay(it.value.first().totalTime),
            historyTimeStamp = parse(it.value.first().historyTimeStamp),
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
