package com.ash8h.barcodeshare

import com.ash8h.barcodeshare.data.BarcodeDatabase
import com.ash8h.barcodeshare.data.BarcodeRepository
import com.ash8h.barcodeshare.ui.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModules = module {
    single { BarcodeDatabase.build(androidApplication()) }
    single { (get() as BarcodeDatabase).barcodeDao() }
    single { BarcodeRepository(get())}
    viewModel { MainViewModel(get()) }
}
