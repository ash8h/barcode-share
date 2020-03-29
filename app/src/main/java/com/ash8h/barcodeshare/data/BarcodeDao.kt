package com.ash8h.barcodeshare.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ash8h.barcodeshare.data.entity.Barcode

@Dao
interface BarcodeDao {

    @Query("SELECT * FROM barcode")
    suspend fun getAll(): List<Barcode>

    @Insert
    suspend fun insertAll(vararg barcodes: Barcode)

    @Delete
    suspend fun delete(barcode: Barcode)
}
