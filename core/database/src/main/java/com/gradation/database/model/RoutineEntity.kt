package com.gradation.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


/*
Params
weight : 무게
repetition : 횟수
 */
@Entity(tableName = "routines")
data class RoutineEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title : String,


    @Embedded
    @ColumnInfo(name = "work_set_list")
    val workSetList: List<WorkSetEntity>,

    )
