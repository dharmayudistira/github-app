package com.pandecode.githubapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.pandecode.data.domain.model.SearchUserItem

class SearchUserDiffCallback : DiffUtil.ItemCallback<SearchUserItem>() {
    override fun areItemsTheSame(oldItem: SearchUserItem, newItem: SearchUserItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SearchUserItem, newItem: SearchUserItem): Boolean {
        return oldItem == newItem
    }

}