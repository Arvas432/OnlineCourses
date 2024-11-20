package com.example.onlinecourses.di

import com.example.onlinecourses.domain.search.SearchInteractor
import com.example.onlinecourses.domain.search.impl.SearchInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    factory<SearchInteractor> {
        SearchInteractorImpl(get())
    }
}