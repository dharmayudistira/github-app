package com.pandecode.githubapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pandecode.data.domain.model.User
import com.pandecode.githubapp.databinding.ItemSearchBinding
import com.pandecode.githubapp.utils.SearchUserDiffCallback
import com.pandecode.githubapp.utils.loadAsCircle

class SearchUserAdapter :
    ListAdapter<User, SearchUserAdapter.ViewHolder>(SearchUserDiffCallback()) {

    private lateinit var onSearchClickCallback: OnSearchClickCallback

    fun setOnSearchClickCallback(onSearchClickCallback: OnSearchClickCallback) {
        this.onSearchClickCallback = onSearchClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchUserAdapter.ViewHolder {
        val view = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchUserAdapter.ViewHolder, position: Int) {
        val user = getItem(position) as User
        holder.bind(user)
    }

    inner class ViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {

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
        fun onItemSearchClick(searchUserItem: User)
    }

}