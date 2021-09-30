package com.pandecode.githubapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.navigation.navArgs
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pandecode.data.domain.model.DetailUser
import com.pandecode.data.source.Resource
import com.pandecode.githubapp.R
import com.pandecode.githubapp.adapter.DetailPagerAdapter
import com.pandecode.githubapp.databinding.ActivityDetailUserBinding
import com.pandecode.githubapp.utils.loadAsCircle
import org.koin.android.viewmodel.ext.android.viewModel

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private val args: DetailUserActivityArgs by navArgs()
    private val viewModel: DetailViewModel by viewModel()

    companion object {

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.following,
            R.string.follower
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarDetail.toolbarDetail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        collapsingToolbarListener(args.selectedUser)
        setupTabLayout()

        args.selectedUser.apply {
            viewModel.getDetailUser(this)
        }

        observeData()
    }

    private fun collapsingToolbarListener(selectedUser: String) {
        var isShow = true
        var scrollRange = -1
        binding.appBarDetail.appBarDetailLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange ?: 1
            }
            if (scrollRange + verticalOffset == 0){
                binding.appBarDetail.collapsingToolbarDetail.title = selectedUser
                isShow = true
            } else if (isShow){
                binding.appBarDetail.collapsingToolbarDetail.title = " "
                isShow = false
            }
        })
    }

    private fun setupTabLayout() {
        val viewPager = binding.viewPagerDetail
        val tabLayout = binding.appBarDetail.tabLayoutDetail

        val pagerAdapter = DetailPagerAdapter(this, args.selectedUser)
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun observeData() {
        viewModel.data.observe(this, {
            when (it) {
                Resource.Empty -> {
                    Log.d("DetailViewModel", "EMPTY")
                    showLoading(false)
                }
                is Resource.Error -> {
                    Log.d("DetailViewModel", "ERROR : ${it.errorMessage}")
                    showLoading(false)
                }
                Resource.Loading -> {
                    Log.d("DetailViewModel", "LOADING")
                    showLoading(true)
                }
                is Resource.Success -> {
                    Log.d("DetailViewModel", "SUCCESS")
                    setupUI(it.data)
                    showLoading(false)
                }
            }
        })
    }

    private fun setupUI(data: DetailUser) {
        binding.apply {
            appBarDetail.ivUserAvatarDetail.loadAsCircle(data.avatarUrl)
            appBarDetail.tvUserNameDetail.text = data.name ?: resources.getString(R.string.null_name_message)
            appBarDetail.tvUserLoginDetail.text = data.login
            appBarDetail.tvUserLocationDetail.text =
                data.location ?: resources.getString(R.string.null_location_message)
            appBarDetail.tvUserCompanyDetail.text =
                data.company ?: resources.getString(R.string.null_company_message)
            appBarDetail.tvUserRepositoriesDetail.text = data.publicRepos.toString()
            appBarDetail.tvUserFollowingDetail.text = data.following.toString()
            appBarDetail.tvUserFollowerDetail.text = data.followers.toString()
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.appBarDetail.veilInformationDetail.veil()
        } else {
            binding.appBarDetail.veilInformationDetail.unVeil()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return false
    }
}