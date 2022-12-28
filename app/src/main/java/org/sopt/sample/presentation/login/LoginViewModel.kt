package org.sopt.sample.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.sample.data.remote.request.RequestLogin
import org.sopt.sample.repositories.AuthRepository
import org.sopt.sample.util.state.NetworkState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    //backing Property
    private val _loginResult = MutableLiveData<NetworkState>()
    val loginResult: LiveData<NetworkState>
        get() = _loginResult

    fun login(email: String, pw: String) {
        viewModelScope.launch {
            authRepository.login(RequestLogin(email, pw))
                .onSuccess {
                    _loginResult.value = NetworkState.Success
                }.onFailure {
                    _loginResult.value = NetworkState.Error(it)
                    Timber.tag("SIGN IN FAIL").e("mes : " + it.message)
                }

        }
    }
}