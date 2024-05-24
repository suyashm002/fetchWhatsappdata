package com.example.waproject.inject

import com.example.waproject.services.MyNotifiService
import com.example.waproject.ui.SaveDataViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Define your dependencies here
   // single { MyNotifiService() } // Example: Provide a singleton instance of MyRepository
    viewModel { SaveDataViewModel()}
}