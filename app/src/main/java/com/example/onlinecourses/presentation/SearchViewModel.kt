package com.example.onlinecourses.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinecourses.domain.models.Course
import com.example.onlinecourses.domain.models.ErrorCode
import com.example.onlinecourses.domain.models.ErrorType
import com.example.onlinecourses.domain.search.Resource
import com.example.onlinecourses.domain.search.SearchInteractor
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchInteractor: SearchInteractor
): ViewModel() {
    private val searchState = MutableLiveData<SearchState>()
    val searchTextState = MutableLiveData("")

    fun observeSearchState(): LiveData<SearchState> = searchState

    private var latestSearchState: String? = null
    private val coursesList = mutableListOf<Course>()

    fun onEditorActionDone() {
        search()
    }

    private fun search() {
        coursesList.clear()
        viewModelScope.launch {
            searchInteractor.search().collect { resource ->
                when(resource) {
                    is Resource.Error -> {
                        handleErrorCode(resource.code)
                    }
                    is Resource.Success -> {
                        coursesList.addAll(resource.data)
                        searchState.postValue(SearchState.Content(coursesList))
                    }
                }
            }
        }
    }

    private fun handleErrorCode(code: Int) {
        when (code) {
            ErrorCode.NO_CONNECTION -> {
                searchState.postValue(SearchState.Error(ErrorType.NO_CONNECTION))
            }

            else -> searchState.postValue(SearchState.Error(ErrorType.SERVER_ERROR))
        }
    }
}