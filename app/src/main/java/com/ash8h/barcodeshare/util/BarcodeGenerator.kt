package com.ash8h.barcodeshare.util

import android.graphics.Bitmap
import com.ash8h.barcodeshare.data.entity.Barcode
import com.ash8h.barcodeshare.data.entity.CodeFormat
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

object BarcodeGenerator {

    fun generate(barcode: Barcode, width: Int, height: Int) =
        generate(barcode.data, barcode.format, width, height)

    fun generate(data: String, format: CodeFormat, width: Int, height: Int): Bitmap {
        val encoder = BarcodeEncoder()
        return encoder.encodeBitmap(data, toBarcodeFormat(format), width, height)
    }

    private fun toBarcodeFormat(format: CodeFormat) = when (format) {
        CodeFormat.EAN_13 -> BarcodeFormat.EAN_13
        CodeFormat.EAN_8 -> BarcodeFormat.EAN_8
        CodeFormat.CODE_128 -> BarcodeFormat.CODE_128
        CodeFormat.UPC_A -> BarcodeFormat.UPC_A
        CodeFormat.UPC_E -> BarcodeFormat.UPC_E
        CodeFormat.ITF_14 -> BarcodeFormat.ITF
    }
}