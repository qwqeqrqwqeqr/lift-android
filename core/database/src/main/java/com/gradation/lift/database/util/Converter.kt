package com.gradation.lift.database.util

import android.media.Image
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
    fun jsonTypeToRoutineList(value: String): List<Routine>? {
        val listType = Types.newParameterizedType(List::class.java, Routine::class.java)
        val adapter: JsonAdapter<List<Routine>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun routineListToJsonType(type: List<Routine>): String {
        val listType = Types.newParameterizedType(List::class.java, Routine::class.java)
        val adapter: JsonAdapter<List<Routine>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}

@ProvidedTypeConverter
class WeekTypeConverter @Inject constructor(private val moshi: Moshi) {

    @TypeConverter
    fun jsonTypeToWeekType(value: String): Week? {
        val adapter: JsonAdapter<Week> = moshi.adapter(Week::class.java)
        return adapter.fromJson(value)
    }


    @TypeConverter
    fun weekTypeToJsonType(type: Week): String {
        val adapter: JsonAdapter<Week> = moshi.adapter(Week::class.java)
        return adapter.toJson(type)
    }
}



@ProvidedTypeConverter
class WorkPartTypeConverter @Inject constructor(private val moshi: Moshi) {
    @TypeConverter
    fun jsonTypeToWeekType(value: String): WorkPart? {
        val adapter: JsonAdapter<WorkPart> = moshi.adapter(WorkPart::class.java)
        return adapter.fromJson(value)
    }


    @TypeConverter
    fun weekTypeToJsonType(type: WorkPart): String {
        val adapter: JsonAdapter<WorkPart> = moshi.adapter(WorkPart::class.java)
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