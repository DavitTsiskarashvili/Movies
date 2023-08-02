package com.movies.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.movies.common.extensions.changeBackgroundColor
import com.movies.common.extensions.changeTextStyle
import com.movies.common.utils.C
import com.movies.common.utils.S
import com.movies.databinding.CategoryItemBinding
import com.movies.presentation.base.adapter.base.BaseAdapter
import com.movies.presentation.model.category.Category

class CategoryAdapter : BaseAdapter<Category, CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class CategoryViewHolder(val binding: CategoryItemBinding) :
        BaseViewHolder<Category>(binding) {
        override fun onBind(item: Category, onClickCallback: ((Category) -> Unit)?) {
            with(binding.categoryButton) {
                text = context.getString(item.name)
                val backgroundColor =
                    if (item.isClicked) C.yellow_primary else C.neutral_02_darkest_grey
                val textStyle =
                    if (item.isClicked) S.smallMontserrat_ButtonFocused else S.smallMontserrat_Button

                changeBackgroundColor(backgroundColor)
                changeTextStyle(textStyle)

                setOnClickListener {
                    onClickCallback?.invoke(item)
                }
            }
        }
    }

}