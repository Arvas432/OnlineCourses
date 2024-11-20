package com.example.onlinecourses.di

import com.example.onlinecourses.data.search.SearchRepositoryImpl
import com.example.onlinecourses.domain.search.SearchRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<SearchRepository> {
        SearchRepositoryImpl(get())
    }
}