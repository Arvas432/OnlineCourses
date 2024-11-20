package com.example.onlinecourses.data.search

import com.example.onlinecourses.data.dto.CourseDto
import com.example.onlinecourses.data.dto.CoursesRequest
import com.example.onlinecourses.data.dto.CoursesSearchResponse
import com.example.onlinecourses.data.network.NetworkClient
import com.example.onlinecourses.domain.models.Course
import com.example.onlinecourses.domain.models.ErrorCode
import com.example.onlinecourses.domain.search.Resource
import com.example.onlinecourses.domain.search.SearchRepository

class SearchRepositoryImpl(private val networkClient: NetworkClient): SearchRepository {
    override suspend fun search(): Resource {
        val response = networkClient.doRequest(CoursesRequest)
        val resource = if (response !is CoursesSearchResponse) {
            when (response.resultCode) {
                ErrorCode.NO_CONNECTION -> Resource.Error(ErrorCode.NO_CONNECTION)
                else -> Resource.Error(ErrorCode.BAD_REQUEST)
            }
        } else {
            when (response.resultCode) {
                ErrorCode.SUCCESS -> Resource.Success(response.courses.map { mapDtoToModel(it) })

                ErrorCode.NO_CONNECTION -> Resource.Error(ErrorCode.NO_CONNECTION)
                ErrorCode.BAD_REQUEST -> Resource.Error(ErrorCode.BAD_REQUEST)
                ErrorCode.NOT_FOUND -> Resource.Error(ErrorCode.NOT_FOUND)
                else -> Resource.Error(ErrorCode.BAD_REQUEST)
            }

        }
        return resource
    }
    private fun mapDtoToModel(dto: CourseDto): Course {
        return Course(
            dto.id,
            dto.cover,
            dto.summary,
            dto.price,
            dto.authors,
            dto.title
        )
    }
}