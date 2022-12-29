package org.sopt.sample.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.data.remote.response.ResponseUser
import org.sopt.sample.util.state.UiState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class HomeViewModel : ViewModel() {

    private val userService = ServicePool.userListService
    private val _userResult = MutableLiveData<UiState<List<ResponseUser.UserListInfo>>>()
    val userResult: LiveData<UiState<List<ResponseUser.UserListInfo>>>
        get() = _userResult


    fun getUser() {
        _userResult.value = UiState.Loading
        userService.getUserList().enqueue(object : Callback<ResponseUser> {
            override fun onResponse(
                call: Call<ResponseUser>,
                response: Response<ResponseUser>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _userResult.value = UiState.Success(it.data)
                    }
                } else {
                    _userResult.value = UiState.Empty
                }

            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                _userResult.value = UiState.Error(t.toString())
                Timber.e("mes : " + t.message)
            }

        })
    }
}
