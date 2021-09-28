package com.pandecode.githubapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandecode.data.source.Resource
import com.pandecode.githubapp.adapter.SearchUserAdapter
import com.pandecode.githubapp.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    private lateinit var searchAdapter: SearchUserAdapter

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

        searchAdapter = SearchUserAdapter()

        binding?.rvUserHome?.apply {
            setAdapter(searchAdapter)
            setLayoutManager(LinearLayoutManager(view.context))
            addVeiledItems(10)
        }

        viewModel.data.observe(viewLifecycleOwner, {
            when(it) {
                Resource.Empty -> {
                    binding?.rvUserHome?.unVeil()
                }
                is Resource.Error -> {
                    binding?.rvUserHome?.unVeil()
                }
                Resource.Loading -> {
                    binding?.rvUserHome?.veil()
                }
                is Resource.Success -> {
                    binding?.rvUserHome?.unVeil()
                    searchAdapter.submitList(it.data)
                }
            }
        })

    }

}