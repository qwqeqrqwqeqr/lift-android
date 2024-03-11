package com.gradation.lift.network.dto.history

import com.gradation.lift.model.model.history.History
import com.gradation.lift.model.model.history.HistoryRoutine
import com.gradation.lift.model.model.work.WorkSet
import kotlinx.datetime.LocalDateTime.Companion.parse
import kotlinx.datetime.LocalTime.Companion.fromSecondOfDay
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetHistoryByHistoryIdResponseDto(
    @SerialName("history")
    val history: List<HistoryDto>,
) {
    fun toDomain(): List<History> = this.history.groupBy { it.historyId }.map {
        History(
            historyId = it.value.first().historyId,
            comment = it.value.first().comment,
            score = it.value.first().score,
            progress = it.value.first().progress,
            restTime = fromSecondOfDay(it.value.first().restTime),
            workTime = fromSecondOfDay(it.value.first().workTime),
            totalTime = fromSecondOfDay(it.value.first().totalTime),
            historyTimeStamp = parse(it.value.first().historyTimeStamp),
            historyRoutine = it.value.map { history ->
                HistoryRoutine(
                    id = history.historyRoutine.historyRoutineId,
                    historyId = history.historyRoutine.historyId,
                    workCategoryId = history.historyRoutine.workCategoryId,
                    workCategoryName = history.historyRoutine.workCategoryName,
                    workPart = history.historyRoutine.workPart,
                    workSetList = history.historyRoutine.workWeightList.zip(history.historyRoutine.workRepetitionList)
                        .mapIndexed { index, workSet ->
                            WorkSet(
                                workSetId = index,
                                weight = workSet.first,
                                repetition = workSet.second
                            )
                        }
                )
            }
        )
    }
}
