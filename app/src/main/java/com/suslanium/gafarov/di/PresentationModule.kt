package com.suslanium.gafarov.di

import com.suslanium.gafarov.presentation.viewmodel.RootViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun providePresentationModule() = module {

    viewModel {
        RootViewModel(get(), get(), get(), get(), get(), get())
    }
}