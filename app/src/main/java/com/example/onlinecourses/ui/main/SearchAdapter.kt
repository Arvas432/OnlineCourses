package com.example.onlinecourses.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinecourses.databinding.CourseListItemBinding
import com.example.onlinecourses.domain.models.Course

class SearchAdapter(private val onItemClick: ((Long) -> Unit)) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private var courses: MutableList<Course> = mutableListOf()

    inner class ViewHolder(private val binding: CourseListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(course: Course) {
            with(course) {
                Glide.with(binding.root)
                    .load(cover)
                    .into(binding.courseImageIv)
                binding.courseTitleTv.text = title
                binding.courseDescriptionTv.text = summary
                binding.coursePriceTv.text = price
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CourseListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = courses.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(courses[position]) {
            holder.bind(this)
            holder.itemView.setOnClickListener { onItemClick(this.id ?: 0) }
        }
    }

    fun setItems(items: List<Course>) {
        courses.clear()
        courses.addAll(items)
        notifyDataSetChanged()
    }
}