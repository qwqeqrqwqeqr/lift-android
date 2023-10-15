package com.gradation.lift.database.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.gradation.lift.model.model.common.UnitOfWeight
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.common.toUnitOfWeight
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.model.model.user.Gender
import com.gradation.lift.model.model.user.toGender
import com.gradation.lift.model.model.work.WorkSet
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.LocalTime.Companion.parse as localTimeParse
import kotlinx.datetime.LocalDateTime.Companion.parse as localDateTimeParse
import kotlinx.datetime.LocalDate.Companion.parse as localDateParse

import javax.inject.Inject


@ProvidedTypeConverter
class WeekdayTypeConverter(
    private val moshi: Moshi,
) {
    @TypeConverter
    fun jsonTypeToWeekdayType(value: String): List<Weekday>? {
        val listType = Types.newParameterizedType(List::class.java, Weekday::class.java)
        val adapter: JsonAdapter<List<Weekday>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun weekdayTypeToJsonType(type: List<Weekday>): String {
        val listType = Types.newParameterizedType(List::class.java, Weekday::class.java)
        val adapter: JsonAdapter<List<Weekday>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}


@ProvidedTypeConverter
class LabelTypeConverter(
    private val moshi: Moshi
) {
    @TypeConverter
    fun jsonTypeToLabelType(value: String): List<Label>? {
        val listType = Types.newParameterizedType(List::class.java, Label::class.java)
        val adapter: JsonAdapter<List<Label>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }


    @TypeConverter
    fun labelTypeToJsonType(type: List<Label>): String {
        val listType = Types.newParameterizedType(List::class.java, Label::class.java)
        val adapter: JsonAdapter<List<Label>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}


@ProvidedTypeConverter
class GenderTypeConverter @Inject constructor() {
    @TypeConverter
    fun jsonTypeToGenderType(value: String): Gender {
        return value.toGender()
    }


    @TypeConverter
    fun genderTypeToJsonType(type: Gender): String {
        return type.getGenderValue()
    }
}

@ProvidedTypeConverter
class UnitOfWeightTypeConverter @Inject constructor() {
    @TypeConverter
    fun jsonTypeToUnitOfWeightType(value: String): UnitOfWeight {
        return value.toUnitOfWeight()
    }


    @TypeConverter
    fun unitOfWeightTypeToJsonType(type: UnitOfWeight): String {
        return type.getUnitOfWeightValue()
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
    fun localDateToJsonType(type: LocalTime): String {
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
