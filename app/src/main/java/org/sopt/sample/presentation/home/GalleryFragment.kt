package org.sopt.sample.presentation.home

import android.os.Bundle
import android.view.View
import org.sopt.sample.R
import org.sopt.sample.adapter.RepoAdapter
import org.sopt.sample.base.BindingFragment
import org.sopt.sample.data.Repo
import org.sopt.sample.databinding.FragmentGalleryBinding


class GalleryFragment : BindingFragment<FragmentGalleryBinding>(R.layout.fragment_gallery) {

    private val mockRepoList = listOf<Repo>(
        Repo(
            image = 0,
            name = "Yubeen's Repository",
            author = "",
            type = Repo.TEXT_TYPE
        ),
        Repo(
            image = R.drawable.github,
            name = "FILL-IN",
            author = "Yubeen-park",
            type = Repo.REPO_TYPE
        ),
        Repo(
            image = R.drawable.github,
            name = "FILL-IN",
            author = "Yubeen-park",
            type = Repo.REPO_TYPE
        ),
        Repo(
            image = R.drawable.github,
            name = "FILL-IN",
            author = "Yubeen-park",
            type = Repo.REPO_TYPE
        ),
        Repo(
            image = R.drawable.github,
            name = "FILL-IN",
            author = "Yubeen-park",
            type = Repo.REPO_TYPE
        ),
        Repo(
            image = R.drawable.github,
            name = "FILL-IN",
            author = "Yubeen-park",
            type = Repo.REPO_TYPE
        ),
        Repo(
            image = R.drawable.github,
            name = "FILL-IN",
            author = "Yubeen-park",
            type = Repo.REPO_TYPE
        ),
        Repo(
            image = R.drawable.github,
            name = "FILL-IN",
            author = "Yubeen-park",
            type = Repo.REPO_TYPE
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        val adapter = RepoAdapter(requireContext())
        binding.rvRepos.adapter = adapter
        adapter.setRepoList(mockRepoList)
    }
}