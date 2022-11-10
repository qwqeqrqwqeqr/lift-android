package com.gradation.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gradation.model.data.WorkPart

/*
Params
id: 운동 종목의 아이디
name : 운동 종목의 이름 (ex: 벤치프레스, 풀업, ...)
workPart : 운동종목의 대분류(또는 부위) (ex: 등, 하체, 가슴, 전신, ...)
image : 카테고리의 대표 이미지
shortDescription : 운동에 대한 설명
longDescription : 운동에 대한 설명 본문
customFlag : 해당 운동이 사용자가 설정한 운동인가?
 */

@Entity(tableName = "work-categories")
data class WorkCategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val name : String,

    val workPart: WorkPart,

    val image: String,

    val shortDescription: String,

    val longDescription: String,

    val customFlag: Boolean
)
