package org.sopt.sample.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.AuthNetworkState
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.data.remote.request.RequestLogin
import org.sopt.sample.data.remote.response.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    //backing Property
    private val _loginResult = MutableLiveData<AuthNetworkState>()
    val loginResult: LiveData<AuthNetworkState>
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
                    _loginResult.value = AuthNetworkState.Success
                } else {
                    _loginResult.value = AuthNetworkState.Failure
                }

            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                _loginResult.value = AuthNetworkState.Error(t)
                Log.e("SIGN IN FAIL", "mes : " + t.message)
            }
        })
    }
}