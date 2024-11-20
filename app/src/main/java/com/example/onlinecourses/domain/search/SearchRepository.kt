package com.example.onlinecourses.domain.search

interface SearchRepository {
    suspend fun search(): Resource
}