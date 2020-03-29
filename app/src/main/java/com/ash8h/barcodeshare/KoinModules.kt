package com.ash8h.barcodeshare

import com.ash8h.barcodeshare.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModules = module {
    viewModel { MainViewModel() }
}
