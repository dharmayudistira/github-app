package com.pandecode.githubapp.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.pandecode.data.domain.model.User
import com.pandecode.githubapp.favorite.R
import com.pandecode.githubapp.favorite.adapter.FavoriteAdapter
import com.pandecode.githubapp.favorite.databinding.FragmentFavoriteBinding
import com.pandecode.githubapp.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteFragment : Fragment(), FavoriteAdapter.OnFavoriteClickCallback {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    private val viewModel: FavoriteViewModel by viewModel()

    private val favoriteAdapter: FavoriteAdapter by lazy {
        FavoriteAdapter().also {
            it.setOnFavoriteClickCallback(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(favoriteModule)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(favoriteModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        observeData()
    }

    private fun setupAdapter() {
        binding?.rvUserFavorite?.apply {
            setAdapter(favoriteAdapter)
            setLayoutManager(GridLayoutManager(binding?.root?.context, 2))
            addVeiledItems(10)
        }
    }

    private fun observeData() {
        viewModel.data.observe(requireActivity(), {
            if (it.isNotEmpty()) {
                showEmpty(false)
                favoriteAdapter.submitList(it)
            } else {
                showEmpty(true)
            }
        })
    }

    private fun showEmpty(state: Boolean) {
        val message = activity?.resources?.getString(R.string.empty_data_message)

        binding?.apply {
            if (state) {
                rvUserFavorite.visibility = View.GONE
                layoutEmpty.tvMessageEmpty.text = message
                layoutEmpty.root.visibility = View.VISIBLE
            } else {
                rvUserFavorite.visibility = View.VISIBLE
                layoutEmpty.root.visibility = View.GONE
            }
        }
    }

    override fun onItemFavoriteClick(user: User) {
        val action =
            FavoriteFragmentDirections.actionFavoriteFragmentToDetailUserActivity(user.login)
        findNavController().navigate(action)
    }

}