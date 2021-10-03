package com.pandecode.githubapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.pandecode.data.domain.model.Repository

class RepositoryUserDiffCallback : DiffUtil.ItemCallback<Repository>() {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem == newItem
    }
}