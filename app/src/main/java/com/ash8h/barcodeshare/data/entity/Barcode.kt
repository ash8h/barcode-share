package com.ash8h.barcodeshare.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity
data class Barcode(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val code: String,
    val description: String?,
    val createdDate: ZonedDateTime,
    val updatedDate: ZonedDateTime
)
