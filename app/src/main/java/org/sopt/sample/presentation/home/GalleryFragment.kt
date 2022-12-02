package org.sopt.sample.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import org.sopt.sample.R
import org.sopt.sample.adapter.GalleryAdapter
import org.sopt.sample.adapter.RepoAdapter
import org.sopt.sample.data.Repo
import org.sopt.sample.data.remote.response.ResponseUser
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.databinding.FragmentGalleryBinding
import org.sopt.sample.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
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
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
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