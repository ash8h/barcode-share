package com.ash8h.barcodeshare.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ash8h.barcodeshare.data.entity.Barcode

@Database(entities = [Barcode::class], version = 1)
@TypeConverters(ZonedDateTimeTypeConverter::class, CodeFormatTypeConverter::class)
abstract class BarcodeDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context): BarcodeDatabase = Room.databaseBuilder(
            context,
            BarcodeDatabase::class.java,
            "barcode-database"
        ).build()
    }

    abstract fun barcodeDao(): BarcodeDao
}
