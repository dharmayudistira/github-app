package com.pandecode.githubapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.pandecode.data.domain.model.User

class SearchUserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}