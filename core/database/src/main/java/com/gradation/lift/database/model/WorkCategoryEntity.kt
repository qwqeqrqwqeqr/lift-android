package com.gradation.lift.database.model

import androidx.room.*
import com.gradation.lift.database.util.WorkPartTypeConverter
import com.gradation.lift.domain.model.WorkPart

/*
Params
id: 운동 종목의 아이디
name : 운동 종목의 이름 (ex: 벤치프레스, 풀업, ...)
workPart : 운동종목의 대분류(또는 부위) (ex: 등, 하체, 가슴, 전신, ...)
image : 카테고리의 대표 이미지 경로
shortDescription : 운동에 대한 설명
longDescription : 운동에 대한 설명 본문
customFlag : 해당 운동이 사용자가 설정한 운동인가?
 */

@Entity(tableName = "work_categories")
data class WorkCategoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long=0,

    var name : String,


    @ColumnInfo(name = "work_part")
    @TypeConverters(WorkPartTypeConverter::class)
    val workPart: com.gradation.lift.domain.model.WorkPart,


    val image: String? = null,

    @ColumnInfo(name = "short_description")
    val shortDescription: String="",

    @ColumnInfo(name = "long_description")
    val longDescription: String="",

    @ColumnInfo(name = "custom_flag")
    val customFlag: Boolean
)
