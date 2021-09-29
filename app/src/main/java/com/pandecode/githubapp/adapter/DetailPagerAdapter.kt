package com.pandecode.githubapp.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pandecode.githubapp.ui.detail.fragment.follower.FollowerFragment
import com.pandecode.githubapp.ui.detail.fragment.following.FollowingFragment

class DetailPagerAdapter(activity: AppCompatActivity, private val username: String) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            FollowingFragment.newInstance(username)
        } else {
            FollowerFragment.newInstance(username)
        }
    }
}