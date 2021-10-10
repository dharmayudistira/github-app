package com.pandecode.githubapp.ui.detail.fragment.follower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandecode.data.source.Resource
import com.pandecode.githubapp.R
import com.pandecode.githubapp.adapter.UserAdapter
import com.pandecode.githubapp.databinding.FragmentFollowerBinding
import com.pandecode.githubapp.utils.showSnackbarMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowerFragment : Fragment() {

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding

    private val viewModel: FollowerViewModel by viewModel()

    private val userAdapter: UserAdapter by lazy {
        UserAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(ARG_USERNAME)?.let {
            viewModel.getFollower(it)
        }

        setupAdapter()
        observeData()
    }

    private fun setupAdapter() {
        binding?.rvUserFollower?.apply {
            setAdapter(userAdapter)
            setLayoutManager(LinearLayoutManager(binding?.root?.context))
            addVeiledItems(10)
        }
    }

    private fun observeData() {
        viewModel.dataFollower.observe(requireActivity(), {
            when (it) {
                Resource.Empty -> {
                    showLoading(false)
                    showEmpty(true)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showEmpty(true)
                    binding?.root?.showSnackbarMessage(it.errorMessage)
                }
                Resource.Loading -> {
                    showLoading(true)
                    showEmpty(false)
                }
                is Resource.Success -> {
                    userAdapter.submitList(it.data)

                    showLoading(false)
                    showEmpty(false)
                }
            }
        })
    }

    private fun showLoading(state: Boolean) {
        binding?.apply {
            if (state) {
                rvUserFollower.veil()
            } else {
                rvUserFollower.unVeil()
            }
        }
    }

    private fun showEmpty(state: Boolean) {
        val message = resources.getString(R.string.empty_data_message)

        binding?.apply {
            if (state) {
                rvUserFollower.visibility = View.GONE
                layoutEmpty.tvMessageEmpty.text = message
                layoutEmpty.root.visibility = View.VISIBLE
            } else {
                rvUserFollower.visibility = View.VISIBLE
                layoutEmpty.root.visibility = View.GONE
            }
        }
    }

    companion object {
        private const val ARG_USERNAME = "username"

        @JvmStatic
        fun newInstance(username: String) =
            FollowerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USERNAME, username)
                }
            }

    }
}