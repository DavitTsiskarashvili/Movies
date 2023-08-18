package com.movies.presentation.view.search_and_filter.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movies.common.extensions.setStyle
import com.movies.common.network.CategoryType
import com.movies.databinding.CategoryItemBinding
import com.movies.presentation.view.search_and_filter.adapter.model.CategoryStyle
import com.movies.presentation.view.search_and_filter.adapter.model.CategoryUIModel

class CategoryAdapter(
    private val itemClick: (CategoryType) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var categoryList = listOf<CategoryUIModel>()
    private var selectedIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            binding.categoryButton.setOnClickListener {
                val item = categoryList[bindingAdapterPosition]
                setClick(item)
                itemClick.invoke(item.categoryType)
            }
        }
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.onBind(categoryList[position], selectedIndex)
    }

    private fun setClick(element: CategoryUIModel) {
        val position = categoryList.indexOf(element)
        val previousIndex = selectedIndex
        selectedIndex = position
        notifyItemChanged(position)
        notifyItemChanged(previousIndex)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAdapterList(newList: List<CategoryUIModel>) {
        categoryList = newList
        notifyDataSetChanged()
    }

    class CategoryViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            item: CategoryUIModel,
            selectedIndex: Int,
        ) {
            with(binding.categoryButton) {
                text = context.getString(item.name)
                setStyle(
                    when (selectedIndex == bindingAdapterPosition) {
                        true -> CategoryStyle.ACTIVE
                        false -> CategoryStyle.INACTIVE
                    }
                )
            }
        }
    }
}