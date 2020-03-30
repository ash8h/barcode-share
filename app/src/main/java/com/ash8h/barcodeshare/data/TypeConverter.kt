package com.ash8h.barcodeshare.data

import androidx.room.TypeConverter
import com.ash8h.barcodeshare.data.entity.CodeFormat
import java.time.ZonedDateTime

internal class ZonedDateTimeTypeConverter {

    @TypeConverter
    fun fromZonedDateTime(zonedDateTime: ZonedDateTime): String = zonedDateTime.toString()

    @TypeConverter
    fun toZonedDateTime(stringZonedDateTime: String): ZonedDateTime = ZonedDateTime.parse(stringZonedDateTime)
}

internal class CodeFormatTypeConverter {
    @TypeConverter
    fun fromCodeFormat(codeFormat: CodeFormat): String = codeFormat.name

    @TypeConverter
    fun toCodeFormat(stringCodeFormat: String): CodeFormat = CodeFormat.valueOf(stringCodeFormat)
}
