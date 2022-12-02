package org.sopt.sample.presentation.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.AuthNetworkState
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.data.remote.request.RequestSignup
import org.sopt.sample.data.remote.response.ResponseSignup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val _signUpResult = MutableLiveData<AuthNetworkState>()
    val signUpResult: LiveData<AuthNetworkState>
        get() = _signUpResult

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
                    _signUpResult.value = AuthNetworkState.Success
                } else {
                    _signUpResult.value = AuthNetworkState.Failure
                }
            }

            override fun onFailure(call: Call<ResponseSignup>, t: Throwable) {
                _signUpResult.value = AuthNetworkState.Error(t)
                Log.e("SIGNUP FAIL", "mes : " + t.message)

            }
        })
    }


}