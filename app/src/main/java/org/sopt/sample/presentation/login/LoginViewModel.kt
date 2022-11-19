package org.sopt.sample.presentation.login

import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.HomeActivity
import org.sopt.sample.data.remote.RequestLogin
import org.sopt.sample.data.remote.ResponseLogin
import org.sopt.sample.data.remote.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    //backing Property
    private val _loginResult = MutableLiveData<ResponseLogin>()
    val loginResult: LiveData<ResponseLogin>
        get() = _loginResult
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String>
        get() = _errorMessage
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
                    _loginResult.value = response.body()
//                    binding.etEmail.text = null
//                    binding.etPw.text = null
                } else {
                    //fail_login_toast()
                }

            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                _errorMessage.value = "네트워크 상태가 불안정합니다."
                Log.e("SIGN IN FAIL", "mes : " + t.message)
            }
        })
    }
}