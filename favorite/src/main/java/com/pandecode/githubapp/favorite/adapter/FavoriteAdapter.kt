package com.pandecode.githubapp.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pandecode.data.domain.model.User
import com.pandecode.githubapp.favorite.databinding.ItemFavoriteBinding
import com.pandecode.githubapp.favorite.utils.FavoriteUserDiffCallback
import com.pandecode.githubapp.utils.loadAsCircle

class FavoriteAdapter : ListAdapter<User, FavoriteAdapter.ViewHolder>(FavoriteUserDiffCallback()) {

    private lateinit var onFavoriteClickCallback: OnFavoriteClickCallback

    fun setOnFavoriteClickCallback(onFavoriteClickCallback: OnFavoriteClickCallback) {
        this.onFavoriteClickCallback = onFavoriteClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        val view = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        val user = getItem(position) as User
        holder.bind(user)
    }

    inner class ViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.apply {
                ivAvaterItemFavorite.loadAsCircle(user.avatarUrl)
                tvLoginItemFavorite.text = user.login

                root.setOnClickListener {
                    onFavoriteClickCallback.onItemFavoriteClick(user)
                }

            }
        }

    }

    interface OnFavoriteClickCallback {
        fun onItemFavoriteClick(user: User)
    }
}