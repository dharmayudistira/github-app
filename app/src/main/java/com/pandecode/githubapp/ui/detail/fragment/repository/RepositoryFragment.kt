package com.pandecode.githubapp.ui.detail.fragment.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandecode.data.source.Resource
import com.pandecode.githubapp.R
import com.pandecode.githubapp.adapter.RepositoryAdapter
import com.pandecode.githubapp.databinding.FragmentRepositoryBinding
import com.pandecode.githubapp.utils.showSnackbarMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryFragment : Fragment() {

    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding

    private val repositoryAdapter: RepositoryAdapter by lazy {
        RepositoryAdapter()
    }

    private val viewModel: RepositoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(ARG_USERNAME)?.let {
            viewModel.getRepository(it)
        }

        binding?.rvUserRepository?.apply {
            setAdapter(repositoryAdapter)
            setLayoutManager(LinearLayoutManager(binding?.root?.context))
            addVeiledItems(10)
        }

        viewModel.data.observe(requireActivity(), {
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
                    repositoryAdapter.submitList(it.data)

                    showEmpty(false)
                    showLoading(false)
                }
            }
        })

    }

    private fun showLoading(state: Boolean) {
        binding?.apply {
            if (state) {
                rvUserRepository.veil()
            } else {
                rvUserRepository.unVeil()
            }
        }
    }

    private fun showEmpty(state: Boolean) {
        val message = String.format(resources.getString(R.string.empty_data_message))

        binding?.apply {
            if (state) {
                rvUserRepository.visibility = View.GONE
                layoutEmpty.root.visibility = View.VISIBLE
                layoutEmpty.tvMessageEmpty.text = message
            } else {
                rvUserRepository.visibility = View.VISIBLE
                layoutEmpty.root.visibility = View.GONE
            }
        }
    }

    companion object {
        private const val ARG_USERNAME = "username"

        @JvmStatic
        fun newInstance(username: String) =
            RepositoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USERNAME, username)
                }
            }

    }
}