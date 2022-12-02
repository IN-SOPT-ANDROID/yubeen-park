package org.sopt.sample.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import org.sopt.sample.adapter.GalleryAdapter
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.data.remote.response.ResponseUser
import org.sopt.sample.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding)


    private lateinit var gridManager: GridLayoutManager
    private lateinit var galleryAdapter: GalleryAdapter
    private val userListService = ServicePool.userListService

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
        userListService.getUserList().enqueue(object : Callback<ResponseUser> {
            override fun onResponse(
                call: Call<ResponseUser>,
                response: Response<ResponseUser>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        galleryAdapter.setItems(it.data)
                    }

                }

            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Log.e("Gallery FAIL", "mes : " + t.message)
            }
        })
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