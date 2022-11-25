package com.gradation.lift.database.model

import androidx.room.*
import com.gradation.lift.database.util.RoutineListTypeConverter
import com.gradation.lift.model.data.Routine
import com.gradation.lift.model.data.Week

/*
Params
id : 루틴 세트 아이디
name : 루틴 세트의 이름
shortDescription : 루틴 세트에 대한 설명
longDescription : 루틴 세트에 대한 자세한 설명
routineList : 루틴의 집합
week : 월 화 수 목 금 토 일
usingCount : 해당 루틴을 몇 번 사용했는지 표시

=====예시=====
name : 지옥 불 하체 루틴
shortDescription : 불타는 금요일에 불태우려고 만든 하체 루틴
longDescription : 초반에 스쿼트로 시작하여 ...
routineList: 스쿼트(60kg 12rep 12set), 레그프레스(120kg 10rep 12set), 스쿼트(80kg 12rep 12set)
week : 금
usingCount :5
 */
@Entity(tableName = "routine_sets")
data class RoutineSetEntity (

    @PrimaryKey(autoGenerate = true)
    val id: Long=0,

    var name : String,
    val shortDescription: String="",
    val longDescription: String="",

    @ColumnInfo(name = "routine_list")
    @TypeConverters(RoutineListTypeConverter::class)
    val routineList: List<Routine>,

    val week: Week,

    val usingCount : Int = 0,

    )