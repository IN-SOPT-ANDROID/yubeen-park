package org.sopt.sample.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import org.sopt.sample.R
import org.sopt.sample.adapter.GalleryAdapter
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.util.showSnackbar
import org.sopt.sample.util.state.UiState

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding)

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var galleryAdapter: GalleryAdapter
    private lateinit var dialog: LoadingDialog

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
        observeEvent()
    }

    private fun observeEvent() {
        viewModel.getUser()
        viewModel.userResult.observe(requireActivity()) {
            when (it) {
                is UiState.Success -> {
                    galleryAdapter.setItems(it.items)
                    dialog.dismiss()
                }
                is UiState.Error -> {
                    dialog.dismiss()
                    binding.root.showSnackbar(
                        getString(R.string.network_error),
                        true
                    )
                }
                is UiState.Empty -> {
                    dialog.dismiss()
                    binding.root.showSnackbar(
                        getString(R.string.unexpected_error),
                        true
                    )
                }
                is UiState.Loading -> {
                    dialog = LoadingDialog(requireContext())
                    dialog.show()
                }
            }
        }
    }

    private fun initRecyclerView() {
        val gridManager = GridLayoutManager(requireContext(), SPAN_COUNT)
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