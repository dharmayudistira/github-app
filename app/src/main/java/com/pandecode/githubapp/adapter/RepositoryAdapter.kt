package com.pandecode.githubapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pandecode.data.domain.model.Repository
import com.pandecode.githubapp.R
import com.pandecode.githubapp.databinding.ItemRepositoryBinding
import com.pandecode.githubapp.utils.RepositoryUserDiffCallback

class RepositoryAdapter :
    ListAdapter<Repository, RepositoryAdapter.ViewHolder>(RepositoryUserDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoryAdapter.ViewHolder {
        val view = ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryAdapter.ViewHolder, position: Int) {
        val repository = getItem(position) as Repository
        holder.bind(repository)
    }

    inner class ViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val noDescriptionMessage =
            binding.root.context.resources.getString(R.string.no_description_message)
        private val unknownLanguageMessage =
            binding.root.context.resources.getString(R.string.unknown_language_message)

        fun bind(repository: Repository) {
            binding.apply {
                tvFullNameRepository.text = repository.fullName
                tvNameRepository.text = repository.name
                tvDescriptionRepository.text = repository.description ?: noDescriptionMessage
                chipLanguageRepository.text = repository.language ?: unknownLanguageMessage
                chipForkRepository.text = repository.forksCount.toString()
                chipStarRepository.text = repository.stargazersCount.toString()
            }
        }

    }

}