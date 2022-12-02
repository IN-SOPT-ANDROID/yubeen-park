package org.sopt.sample.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.NetworkState
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.data.remote.response.ResponseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val userService = ServicePool.userListService
    private val _userResult = MutableLiveData<NetworkState>()
    val userResult: LiveData<NetworkState>
        get() = _userResult

    private val _userList = MutableLiveData<List<ResponseUser.userListInfo>>()
    val userList: LiveData<List<ResponseUser.userListInfo>>
        get() = _userList

    fun getUser() {
        userService.getUserList().enqueue(object : Callback<ResponseUser> {
            override fun onResponse(
                call: Call<ResponseUser>,
                response: Response<ResponseUser>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _userList.value = it.data
                    }
                    _userResult.value = NetworkState.Success
                } else {
                    _userResult.value = NetworkState.Failure
                }

            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                _userResult.value = NetworkState.Error(t)
                Log.e("Gallery FAIL", "mes : " + t.message)
            }

        })
    }
}
