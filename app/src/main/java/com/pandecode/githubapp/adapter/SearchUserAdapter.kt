package com.pandecode.githubapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pandecode.data.domain.model.SearchUserItem
import com.pandecode.githubapp.databinding.ItemUserBinding
import com.pandecode.githubapp.utils.SearchUserDiffCallback
import com.pandecode.githubapp.utils.loadAsCircle

class SearchUserAdapter :
    ListAdapter<SearchUserItem, SearchUserAdapter.ViewHolder>(SearchUserDiffCallback()) {

    private lateinit var onSearchClickCallback: OnSearchClickCallback

    fun setOnSearchClickCallback(onSearchClickCallback: OnSearchClickCallback) {
        this.onSearchClickCallback = onSearchClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchUserAdapter.ViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchUserAdapter.ViewHolder, position: Int) {
        val user = getItem(position) as SearchUserItem
        holder.bind(user, onSearchClickCallback)
    }

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: SearchUserItem, callback: OnSearchClickCallback) {

            binding.apply {
                ivUserAvatarItem.loadAsCircle(user.avatarUrl)
                tvUserLoginItem.text = user.login

                root.setOnClickListener {
                    onSearchClickCallback.onItemSearchClick(user)
                }
            }

        }

    }

    interface OnSearchClickCallback {
        fun onItemSearchClick(searchUserItem: SearchUserItem)
    }

}