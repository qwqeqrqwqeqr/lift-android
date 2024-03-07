package com.gradation.lift.database.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.gradation.lift.model.model.work.CheckedWorkSetInfo
import com.gradation.lift.model.model.work.EffectContent
import com.gradation.lift.model.model.work.SequenceContent
import com.gradation.lift.model.model.work.WorkSet
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.LocalDate.Companion.parse as localDateParse
import kotlinx.datetime.LocalDateTime.Companion.parse as localDateTimeParse
import kotlinx.datetime.LocalTime.Companion.parse as localTimeParse


@ProvidedTypeConverter
class StringListTypeConverter(
    private val moshi: Moshi,
) {
    @TypeConverter
    fun jsonTypeToStringListType(value: String): List<String>? {
        val listType = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun stringListTypeToJsonType(type: List<String>): String {
        val listType = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}

@ProvidedTypeConverter
class IntListTypeConverter(
    private val moshi: Moshi,
) {
    @TypeConverter
    fun jsonTypeToIntListType(value: String): List<Int>? {
        val listType = Types.newParameterizedType(List::class.java, Integer::class.java)
        val adapter: JsonAdapter<List<Int>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun intListTypeToJsonType(type: List<Int>): String {
        val listType = Types.newParameterizedType(List::class.java, Integer::class.java)
        val adapter: JsonAdapter<List<Int>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}


@ProvidedTypeConverter
class CheckedWorkSetInfoListTypeConverter(
    private val moshi: Moshi,
) {
    @TypeConverter
    fun jsonTypeToIntListType(value: String): List<CheckedWorkSetInfo>? {
        val listType = Types.newParameterizedType(List::class.java, CheckedWorkSetInfo::class.java)
        val adapter: JsonAdapter<List<CheckedWorkSetInfo>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun intListTypeToJsonType(type: List<CheckedWorkSetInfo>): String {
        val listType = Types.newParameterizedType(List::class.java, CheckedWorkSetInfo::class.java)
        val adapter: JsonAdapter<List<CheckedWorkSetInfo>> = moshi.adapter(listType)
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
class SequenceContentListTypeConverter(
    private val moshi: Moshi,
) {

    @TypeConverter
    fun jsonTypeToSequenceContentList(value: String): List<SequenceContent>? {
        val listType = Types.newParameterizedType(List::class.java, SequenceContent::class.java)
        val adapter: JsonAdapter<List<SequenceContent>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun sequenceContentListToJsonType(type: List<SequenceContent>): String {
        val listType = Types.newParameterizedType(List::class.java, SequenceContent::class.java)
        val adapter: JsonAdapter<List<SequenceContent>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}


@ProvidedTypeConverter
class EffectContentListTypeConverter(
    private val moshi: Moshi,
) {

    @TypeConverter
    fun jsonTypeToEffectContentList(value: String): List<EffectContent>? {
        val listType = Types.newParameterizedType(List::class.java, EffectContent::class.java)
        val adapter: JsonAdapter<List<EffectContent>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun effectContentListToJsonType(type: List<EffectContent>): String {
        val listType = Types.newParameterizedType(List::class.java, EffectContent::class.java)
        val adapter: JsonAdapter<List<EffectContent>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}


@ProvidedTypeConverter
class LocalTimeTypeConverter {
    @TypeConverter
    fun jsonTypeToLocalTime(value: String): LocalTime {
        return localTimeParse(value)
    }

    @TypeConverter
    fun localTimeToJsonType(type: LocalTime): String {
        return type.toString()
    }
}


@ProvidedTypeConverter
class LocalDateTypeConverter {
    @TypeConverter
    fun jsonTypeToLocalDate(value: String): LocalDate {
        return localDateParse(value)
    }

    @TypeConverter
    fun localDateToJsonType(type: LocalDate): String {
        return type.toString()
    }
}


@ProvidedTypeConverter
class LocalDateTimeTypeConverter {

    @TypeConverter
    fun jsonTypeToLocalDateTime(value: String): LocalDateTime {
        return localDateTimeParse(isoString = value)
    }

    @TypeConverter
    fun localDateTimeToJsonType(type: LocalDateTime): String {
        return type.toString()
    }
}
