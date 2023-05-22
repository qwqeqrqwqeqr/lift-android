package com.gradation.lift.database.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.gradation.lift.model.data.*
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
class WeekTypeConverter @Inject constructor(private val moshi: Moshi) {

    @TypeConverter
    fun jsonTypeToWeekType(value: String): com.gradation.lift.domain.model.Week? {
        val adapter: JsonAdapter<com.gradation.lift.domain.model.Week> = moshi.adapter(com.gradation.lift.domain.model.Week::class.java)
        return adapter.fromJson(value)
    }


    @TypeConverter
    fun weekTypeToJsonType(type: com.gradation.lift.domain.model.Week): String {
        val adapter: JsonAdapter<com.gradation.lift.domain.model.Week> = moshi.adapter(com.gradation.lift.domain.model.Week::class.java)
        return adapter.toJson(type)
    }
}



@ProvidedTypeConverter
class WorkPartTypeConverter @Inject constructor(private val moshi: Moshi) {
    @TypeConverter
    fun jsonTypeToWeekType(value: String): com.gradation.lift.domain.model.WorkPart? {
        val adapter: JsonAdapter<com.gradation.lift.domain.model.WorkPart> = moshi.adapter(com.gradation.lift.domain.model.WorkPart::class.java)
        return adapter.fromJson(value)
    }


    @TypeConverter
    fun weekTypeToJsonType(type: com.gradation.lift.domain.model.WorkPart): String {
        val adapter: JsonAdapter<com.gradation.lift.domain.model.WorkPart> = moshi.adapter(com.gradation.lift.domain.model.WorkPart::class.java)
        return adapter.toJson(type)
    }
}


//@TypeConverter
//fun fromTimestamp(value: Long?): Date? {
//    return if (value == null) null else Date(value)
//}
//
//@TypeConverter
//fun dateToTimestamp(date: Date?): Long? {
//    return date?.time
//}