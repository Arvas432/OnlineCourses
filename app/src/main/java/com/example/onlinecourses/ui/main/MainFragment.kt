package com.example.onlinecourses.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.example.onlinecourses.databinding.FragmentMainBinding
import com.example.onlinecourses.domain.models.Course
import com.example.onlinecourses.domain.models.ErrorType
import com.example.onlinecourses.presentation.SearchState
import com.example.onlinecourses.presentation.SearchViewModel
import com.example.onlinecourses.util.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BindingFragment<FragmentMainBinding>() {
    private val viewModel: SearchViewModel by viewModel()
    private val adapter by lazy {
        SearchAdapter {}
    }
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeSearchState().observe(viewLifecycleOwner) { state ->
            when(state) {
                is SearchState.Content -> {
                    showContent(state.data)
                }
                is SearchState.Error -> {
                    showError(state.type)
                }
            }
        }
        binding.searchRv.adapter = adapter
        binding.searchEt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.onEditorActionDone()
            }
            false
        }
    }
    private fun showContent(data: List<Course>) {
        adapter.setItems(data)
    }

    private fun showError(type: ErrorType) {
        Toast.makeText(this.requireContext(), "Error $type", Toast.LENGTH_LONG).show()
    }
}