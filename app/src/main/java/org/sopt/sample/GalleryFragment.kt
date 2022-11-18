package org.sopt.sample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import org.sopt.sample.adapter.GalleryAdapter
import org.sopt.sample.data.UserInfo
import org.sopt.sample.data.remote.RequestLogin
import org.sopt.sample.data.remote.ResponseLogin
import org.sopt.sample.data.remote.ResponseUserList
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.databinding.FragmentGalleryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding)

    private lateinit var gridManager: GridLayoutManager
    private lateinit var galleryAdapter : GalleryAdapter
    private val userListService = ServicePool.userListService
    private var galleryList: MutableList<ResponseUserList.userListInfo> = mutableListOf()

    companion object {
        const val SPAN_COUNT = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initUserInfo()
    }

    private fun initUserInfo() {
        userListService.getUserList().enqueue(object : Callback<ResponseUserList> {
            override fun onResponse(
                call: Call<ResponseUserList>,
                response: Response<ResponseUserList>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        galleryList.clear()
                        for (user in it.data) {
                            galleryList.add(user)
                            Log.i("userinfo", user.toString())
                        }
                        Log.i("body", it.toString())

                        galleryAdapter.setItems(galleryList)
                    }

                }

            }

            override fun onFailure(call: Call<ResponseUserList>, t: Throwable) {
                Log.e("Gallery FAIL", "mes : " + t.message)
            }
        })
    }

    private fun initRecyclerView() {
        gridManager = GridLayoutManager(requireContext(), SPAN_COUNT)
        galleryAdapter = GalleryAdapter(requireContext())
        binding.rvGallery.apply {
            layoutManager = gridManager
            adapter = galleryAdapter
        }
        galleryAdapter.setItems(galleryList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}