package com.pandecode.githubapp.ui.home

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandecode.data.domain.model.User
import com.pandecode.data.source.Resource
import com.pandecode.githubapp.R
import com.pandecode.githubapp.adapter.SearchUserAdapter
import com.pandecode.githubapp.databinding.FragmentHomeBinding
import com.pandecode.githubapp.utils.hideKeyboard
import com.pandecode.githubapp.utils.showSnackbarMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), SearchUserAdapter.OnSearchClickCallback {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    private val searchAdapter: SearchUserAdapter by lazy {
        SearchUserAdapter().also {
            it.setOnSearchClickCallback(this)
        }
    }

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupSearchView()
        observeData()

    }

    private fun setupAdapter() {
        binding?.rvUserHome?.apply {
            setAdapter(searchAdapter)
            setLayoutManager(LinearLayoutManager(binding?.root?.context))
            addVeiledItems(10)
        }
    }

    private fun setupSearchView() {
        val manager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager

        binding?.searchUserHome?.apply {
            setSearchableInfo(manager.getSearchableInfo(requireActivity().componentName))
            queryHint = resources.getString(R.string.query_hints)

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.isNullOrEmpty()) {
                        viewModel.searchUser(query)
                        hideKeyboard()
                    }

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun observeData() {
        viewModel.data.observe(viewLifecycleOwner, {
            when (it) {
                Resource.Empty -> {
                    showLoading(false)
                    showEmptyResult(true)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showEmptyResult(true)
                    binding?.root?.showSnackbarMessage(it.errorMessage)
                }
                Resource.Loading -> {
                    showLoading(true)
                    showEmptyResult(false)
                }
                is Resource.Success -> {
                    searchAdapter.submitList(it.data)
                    showEmptyResult(false)
                    showLoading(false)
                }
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.rvUserHome?.veil()
        } else {
            binding?.rvUserHome?.unVeil()
        }
    }

    private fun showEmptyResult(state: Boolean) {
        val username = binding?.searchUserHome?.query?.toString()
        val message = String.format(resources.getString(R.string.empty_search_message), username)

        binding?.apply {
            if (state) {
                rvUserHome.visibility = View.GONE
                layoutEmpty.tvMessageEmpty.text = message
                layoutEmpty.root.visibility = View.VISIBLE
            } else {
                rvUserHome.visibility = View.VISIBLE
                layoutEmpty.root.visibility = View.GONE
            }
        }
    }

    override fun onItemSearchClick(searchUserItem: User) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToDetailUserActivity(searchUserItem.login)
        findNavController().navigate(action)
    }
}