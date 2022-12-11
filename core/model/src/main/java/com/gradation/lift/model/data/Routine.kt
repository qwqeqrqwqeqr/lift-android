package com.gradation.lift.model.data

/*
Params
name : 이름 (work category 에 지정되어 있는 이름을 통해 참조함)
workSetList : 세트의 총합  (얘룰 둘어, workSetList 내 아이템이 7개 있으면 총 7세트가 됌)
 */
data class Routine(
    val name : String,
    val workSetList: List<WorkSet>,
    val maxWeight: Double = workSetList.maxOf { it.weight },
    val minWeight: Double = workSetList.minOf { it.weight },
    val totalWeight : Double = workSetList.sumOf { it.weight },
    val maxRepetition : Int = workSetList.maxOf { it.repetition },
    val minRepetition : Int =  workSetList.minOf { it.repetition },
    val totalRepetition : Int = workSetList.sumOf { it.repetition },
)
