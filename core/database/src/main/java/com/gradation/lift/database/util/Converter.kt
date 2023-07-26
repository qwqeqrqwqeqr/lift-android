package com.gradation.lift.database.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.gradation.lift.database.model.WorkPartEntity
import com.gradation.lift.model.work.WorkPart
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class RoutineListTypeConverter @Inject constructor(private val moshi: Moshi) {

    @TypeConverter
    fun jsonTypeToRoutineList(value: String): List<com.gradation.lift.domain.model.Routine>? {
        val listType = Types.newParameterizedType(List::class.java, com.gradation.lift.domain.model.Routine::class.java)
        val adapter: JsonAdapter<List<com.gradation.lift.domain.model.Routine>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun routineListToJsonType(type: List<com.gradation.lift.domain.model.Routine>): String {
        val listType = Types.newParameterizedType(List::class.java, com.gradation.lift.domain.model.Routine::class.java)
        val adapter: JsonAdapter<List<com.gradation.lift.domain.model.Routine>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}





@ProvidedTypeConverter
class WorkPartTypeConverter @Inject constructor(private val moshi: Moshi) {
    @TypeConverter
    fun jsonTypeToWeekType(value: String): WorkPartEntity? {
        val adapter: JsonAdapter<WorkPartEntity> = moshi.adapter(WorkPartEntity::class.java)
        return adapter.fromJson(value)
    }


    @TypeConverter
    fun weekTypeToJsonType(type: WorkPartEntity): String {
        val adapter: JsonAdapter<WorkPartEntity> = moshi.adapter(WorkPartEntity::class.java)
        return adapter.toJson(type)
    }
}

