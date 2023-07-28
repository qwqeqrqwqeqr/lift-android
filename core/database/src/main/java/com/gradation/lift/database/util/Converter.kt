package com.gradation.lift.database.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.work.WorkSet
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import javax.inject.Inject



@ProvidedTypeConverter
class WeekdayTypeConverter @Inject constructor(private val moshi: Moshi) {
    @TypeConverter
    fun jsonTypeToWeekdayType(value: String): Weekday? {
        val adapter: JsonAdapter<Weekday> = moshi.adapter(Weekday::class.java)
        return adapter.fromJson(value)
    }


    @TypeConverter
    fun weekdayTypeToJsonType(type: Weekday): String {
        val adapter: JsonAdapter<Weekday> = moshi.adapter(Weekday::class.java)
        return adapter.toJson(type)
    }
}




@ProvidedTypeConverter
class WorkSetListTypeConverter(
    private val moshi: Moshi,
) {

    @TypeConverter
    fun jsonTypeToWorkSetList(value: String): List<WorkSet>? {
        val listType = Types.newParameterizedType(List::class.java, WorkSet::class.java)
        val adapter: JsonAdapter<List<WorkSet>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun workSetListToJsonType(type: List<WorkSet>): String {
        val listType = Types.newParameterizedType(List::class.java, WorkSet::class.java)
        val adapter: JsonAdapter<List<WorkSet>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}



@ProvidedTypeConverter
class LocalTimeTypeConverter(
    private val moshi: Moshi,
) {

    @TypeConverter
    fun jsonTypeToLocalTime(value: String): LocalTime? {
        val listType = Types.newParameterizedType(LocalTime::class.java)
        val adapter: JsonAdapter<LocalTime> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun localTimeToJsonType(type: LocalTime): String {
        val listType = Types.newParameterizedType(LocalTime::class.java)
        val adapter: JsonAdapter<LocalTime> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}



@ProvidedTypeConverter
class LocalDateTimeTypeConverter(
    private val moshi: Moshi,
) {

    @TypeConverter
    fun jsonTypeToLocalDateTime(value: String): LocalDateTime? {
        val listType = Types.newParameterizedType(LocalDateTime::class.java)
        val adapter: JsonAdapter<LocalDateTime> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun localDateTimeToJsonType(type: LocalDateTime): String {
        val listType = Types.newParameterizedType(LocalDateTime::class.java)
        val adapter: JsonAdapter<LocalDateTime> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}
