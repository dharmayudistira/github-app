package com.pandecode.githubapp.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.pandecode.data.domain.model.DetailUser
import com.pandecode.data.source.Resource
import com.pandecode.githubapp.R
import com.pandecode.githubapp.adapter.DetailPagerAdapter
import com.pandecode.githubapp.databinding.ActivityDetailUserBinding
import com.pandecode.githubapp.utils.loadAsCircle
import com.pandecode.githubapp.utils.loadAsCircleFromDrawable
import com.pandecode.githubapp.utils.setIconFromDrawableId
import com.pandecode.githubapp.utils.showSnackbarMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailUserActivity : AppCompatActivity() {

    private val binding: ActivityDetailUserBinding by lazy {
        ActivityDetailUserBinding.inflate(layoutInflater)
    }

    private val args: DetailUserActivityArgs by navArgs()
    private val viewModel: DetailViewModel by viewModel()

    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarDetail.toolbarDetail)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupTabLayout()

        args.selectedUser.apply {
            viewModel.getDetailUser(this)
            viewModel.checkFavorite(this)
        }

        observeData()
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
        viewModel.isFavorite.observe(this, {
            isFavorite = it

            binding.fabFavoriteDetail.apply {
                if (isFavorite) {
                    this.setIconFromDrawableId(R.drawable.ic_favorite_on)
                } else {
                    this.setIconFromDrawableId(R.drawable.ic_favorite_off)
                }
            }
        })

        viewModel.data.observe(this, {
            when (it) {
                Resource.Empty -> {
                    showLoading(false)
                }
                is Resource.Error -> {
                    showError(it.errorMessage)
                    showLoading(false)
                }
                Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    setupUI(it.data)
                    showLoading(false)
                }
            }
        })
    }

    private fun setupUI(data: DetailUser) {
        binding.appBarDetail.apply {
            ivUserAvatarDetail.loadAsCircle(data.avatarUrl)
            tvUserNameDetail.text = data.name ?: resources.getString(R.string.null_name_message)
            tvUserLoginDetail.text = data.login
            tvUserLocationDetail.text =
                data.location ?: resources.getString(R.string.null_location_message)
            tvUserCompanyDetail.text =
                data.company ?: resources.getString(R.string.null_company_message)
            tvUserRepositoriesDetail.text = data.publicRepos.toString()
            tvUserFollowingDetail.text = data.following.toString()
            tvUserFollowerDetail.text = data.followers.toString()
        }

        binding.fabFavoriteDetail.setOnClickListener {
            if (isFavorite) {
                it.showSnackbarMessage(resources.getString(R.string.remove_favorite_message))
                viewModel.deleteFromDatabase(data)
            } else {
                it.showSnackbarMessage(resources.getString(R.string.add_favorite_message))
                viewModel.insertToDatabase(data)
            }
        }
    }

    private fun showError(message: String) {
        binding.appBarDetail.apply {
            ivUserAvatarDetail.loadAsCircleFromDrawable(R.drawable.ic_image_broken)
            tvUserNameDetail.text = resources.getString(R.string.null_name_message)
            tvUserLoginDetail.text = resources.getString(R.string.null_login_message)
            tvUserLocationDetail.text = resources.getString(R.string.null_location_message)
            tvUserCompanyDetail.text = resources.getString(R.string.null_company_message)
            tvUserRepositoriesDetail.text = resources.getString(R.string.dummy_number)
            tvUserFollowingDetail.text = resources.getString(R.string.dummy_number)
            tvUserFollowerDetail.text = resources.getString(R.string.dummy_number)
        }

        binding.root.showSnackbarMessage(message)
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

    companion object {

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.repositories,
            R.string.following,
            R.string.follower
        )

    }
}