package com.ash8h.barcodeshare.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ash8h.barcodeshare.data.BarcodeRepository
import com.ash8h.barcodeshare.data.entity.Barcode
import kotlinx.coroutines.launch

class MainViewModel(
    private val barcodeRepository: BarcodeRepository
) : ViewModel() {

    private val _barcodes = MutableLiveData<List<Barcode>>()
    val barcodes: LiveData<List<Barcode>> = _barcodes

    fun fetchBarcodes() {
        viewModelScope.launch {
            _barcodes.value = barcodeRepository.getBarcodes()
        }
    }
}
