package org.sopt.sample.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.data.remote.request.RequestLogin
import org.sopt.sample.data.remote.response.ResponseLogin
import org.sopt.sample.util.state.NetworkState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class LoginViewModel : ViewModel() {

    //backing Property
    private val _loginResult = MutableLiveData<NetworkState>()
    val loginResult: LiveData<NetworkState>
        get() = _loginResult
    private val loginService = ServicePool.authService

    fun login(email: String, pw: String) {
        loginService.login(
            RequestLogin(
                email,
                pw
            )
        ).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(
                call: Call<ResponseLogin>,
                response: Response<ResponseLogin>
            ) {
                if (response.isSuccessful) {
                    _loginResult.value = NetworkState.Success
                } else {
                    _loginResult.value = NetworkState.Failure
                }

            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                _loginResult.value = NetworkState.Error(t)
                Timber.tag("SIGN IN FAIL").e("mes : " + t.message)
            }
        })
    }
}