package com.gradation.lift.network.dto.history


import com.gradation.lift.model.model.history.History
import com.gradation.lift.model.model.history.HistoryRoutine
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkSet
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetHistoryResponseDto(
    @SerialName("history")
    val history: List<HistoryDto>,
) {
    fun toDomain(): List<History> = this.history.groupBy { it.historyId }.map {
        History(
            historyId = it.value.first().historyId,
            comment = it.value.first().comment,
            score = it.value.first().score,
            progress = it.value.first().progress,
            workTime = LocalTime.fromSecondOfDay(it.value.first().workTime.toInt()),
            restTime = LocalTime.fromSecondOfDay(it.value.first().restTime.toInt()),
            totalTime = LocalTime.fromSecondOfDay(it.value.first().totalTime.toInt()),
            historyTimeStamp = LocalDateTime.parse(it.value.first().historyTimeStamp),
            historyRoutine = it.value.map { history ->
                HistoryRoutine(
                    id = history.historyRoutine.historyRoutineId,
                    historyId = history.historyRoutine.historyId,
                    workCategory = WorkCategory(
                        id = history.historyRoutine.workCategory.id,
                        name = history.historyRoutine.workCategory.name,
                        workPart = history.historyRoutine.workCategory.workPart,
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
