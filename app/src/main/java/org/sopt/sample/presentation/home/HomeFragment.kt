package org.sopt.sample.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import org.sopt.sample.R
import org.sopt.sample.adapter.RepoAdapter
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.util.showSnackbar
import org.sopt.sample.util.state.UiState


class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var galleryAdapter: RepoAdapter
    private lateinit var dialog: LoadingDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
        addObservers()
    }

    private fun initLayout() {
        val gridManager = GridLayoutManager(requireContext(), SPAN_COUNT)
        galleryAdapter = RepoAdapter()
        binding.rvGallery.apply {
            layoutManager = gridManager
            adapter = galleryAdapter
        }
    }

    private fun addObservers() {
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


    companion object {
        const val SPAN_COUNT = 2
    }

}