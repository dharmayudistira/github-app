package com.pandecode.githubapp.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pandecode.githubapp.ui.detail.fragment.follower.FollowerFragment
import com.pandecode.githubapp.ui.detail.fragment.following.FollowingFragment
import com.pandecode.githubapp.ui.detail.fragment.repository.RepositoryFragment

class DetailPagerAdapter(activity: AppCompatActivity, private val username: String) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RepositoryFragment.newInstance(username)
            1 -> FollowingFragment.newInstance(username)
            else -> FollowerFragment.newInstance(username)
        }
    }
}