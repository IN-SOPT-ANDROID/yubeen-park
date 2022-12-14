package org.sopt.sample.presentation.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.NetworkState
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.data.remote.request.RequestSignup
import org.sopt.sample.data.remote.response.ResponseSignup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val _signUpResult = MutableLiveData<NetworkState>()
    val signUpResult: LiveData<NetworkState>
        get() = _signUpResult

    private val signUpService = ServicePool.authService

    val userName = MutableLiveData<String>("")
    val userEmail = MutableLiveData<String>("")
    val userPassword = MutableLiveData<String>("")


    val emailFlag =
        Transformations.map(userEmail) { email -> emailRegex(email) || email.isEmpty() }
    val pwFlag =
        Transformations.map(userPassword) { pw -> passwordRegex(pw) || pw.isEmpty() }


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
                    _signUpResult.value = NetworkState.Success
                } else {
                    _signUpResult.value = NetworkState.Failure
                }
            }

            override fun onFailure(call: Call<ResponseSignup>, t: Throwable) {
                _signUpResult.value = NetworkState.Error(t)
                Log.e("SIGNUP FAIL", "mes : " + t.message)

            }
        })
    }

    private fun emailRegex(email: String): Boolean {
        return email.matches("^(?=.*[a-zA-Z])(?=.*[0-9]).{6,10}$".toRegex())
    }

    private fun passwordRegex(password: String): Boolean {
        return password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$&+?@#<>^*%!]).{6,12}$".toRegex())
    }
}