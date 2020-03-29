package com.ash8h.barcodeshare.data

import androidx.room.TypeConverter
import java.time.ZonedDateTime

internal class ZonedDateTimeTypeConverter {

    @TypeConverter
    fun fromZonedDateTime(zonedDateTime: ZonedDateTime): String = zonedDateTime.toString()

    @TypeConverter
    fun toZonedDateTime(stringZonedDateTime: String): ZonedDateTime = ZonedDateTime.parse(stringZonedDateTime)
}
