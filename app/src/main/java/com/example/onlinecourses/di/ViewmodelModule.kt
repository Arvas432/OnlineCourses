package com.example.onlinecourses.di

import com.example.onlinecourses.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel<SearchViewModel> {
        SearchViewModel(get())
    }
}