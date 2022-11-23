package org.sopt.sample.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.sample.R
import org.sopt.sample.adapter.RepoAdapter
import org.sopt.sample.data.Repo
import org.sopt.sample.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding)

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RepoAdapter(requireContext())
        binding.rvRepos.adapter = adapter
        adapter.setRepoList(mockRepoList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}