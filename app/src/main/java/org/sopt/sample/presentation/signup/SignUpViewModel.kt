package org.sopt.sample.presentation.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.request.RequestSignup
import org.sopt.sample.data.remote.response.ResponseSignup
import org.sopt.sample.data.remote.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val _signUpResult = MutableLiveData<ResponseSignup>()
    val signUpResult: LiveData<ResponseSignup>
        get() = _signUpResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    private val signUpService = ServicePool.authService

    fun signUp(email: String, pw: String, name: String) {
        signUpService.signup(
            RequestSignup(
                email, pw, name
            )
        ).enqueue(object : Callback<ResponseSignup> {
            override fun onResponse(
                call: Call<ResponseSignup>,
                response: Response<ResponseSignup>
            ) {
                if (response.isSuccessful) {
                    _signUpResult.value = response.body()
                } else {
                    _errorMessage.value = "회원가입이 불가합니다."
                }
            }

            override fun onFailure(call: Call<ResponseSignup>, t: Throwable) {
                _errorMessage.value = "네트워크가 불안정합니다."
                Log.e("SIGNUP FAIL", "mes : " + t.message)

            }
        })
    }


}