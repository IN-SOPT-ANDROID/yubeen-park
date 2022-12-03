package org.sopt.sample.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.R
import org.sopt.sample.adapter.GalleryAdapter
import org.sopt.sample.data.remote.NetworkState
import org.sopt.sample.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding)

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var gridManager: GridLayoutManager
    private lateinit var galleryAdapter: GalleryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initUserInfo()
    }

    private fun initUserInfo() {
        viewModel.getUser()
        viewModel.userResult.observe(requireActivity()) {
            when (it) {
                is NetworkState.Success -> {
                    viewModel.userList.value?.let {
                        galleryAdapter.setItems(it)
                    }
                }
                is NetworkState.Failure -> failUserSnackbar(getString(R.string.unexpected_error))
                is NetworkState.Error -> failUserSnackbar(getString(R.string.network_error))
            }
        }
    }

    private fun failUserSnackbar(errorMessage: String) {
        Snackbar.make(
            binding.root,
            errorMessage,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun initRecyclerView() {
        gridManager = GridLayoutManager(requireContext(), SPAN_COUNT)
        galleryAdapter = GalleryAdapter()
        binding.rvGallery.apply {
            layoutManager = gridManager
            adapter = galleryAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val SPAN_COUNT = 2
    }

}