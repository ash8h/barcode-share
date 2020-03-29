package com.ash8h.barcodeshare.data

class BarcodeRepository(private val barcodeDao: BarcodeDao) {

    suspend fun getBarcodes() = barcodeDao.getAll()
}
