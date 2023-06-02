package org.sopt.sample.presentation.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.sample.R
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.presentation.login.LoginActivity
import org.sopt.sample.util.showSnackbar
import org.sopt.sample.util.state.NetworkState
import timber.log.Timber

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        addListeners()
        addObservers()
    }

    private fun addListeners() {
        binding.btnSignupFinish.setOnClickListener {
            viewModel.signUp(
                binding.etEmail.text.toString(),
                binding.etPw.text.toString(),
                binding.etName.text.toString()
            )
        }
    }

    private fun addObservers() {
        viewModel.emailFlag.observe(this) {
            Timber.i("emailFlag : $it")
            Timber.i("emailCheck : " + binding.layoutEtEmail.isErrorEnabled.toString())
            if (it) {//이메일 양식이 맞을 때
                binding.layoutEtEmail.error = null
                binding.layoutEtEmail.isErrorEnabled = false
            } else {
                binding.layoutEtEmail.error = getString(R.string.signup_email_error)
            }
        }

        viewModel.pwFlag.observe(this) {
            if (it) {//이메일 양식이 맞을 때
                binding.layoutEtPw.error = null
                binding.layoutEtPw.isErrorEnabled = false
            } else {
                binding.layoutEtPw.error = getString(R.string.signup_pw_error)
            }
        }

        viewModel.signUpResult.observe(this) {
            when (it) {
                is NetworkState.Success -> {
                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    setResult(RESULT_OK, intent)
                    if (!isFinishing) finish()
                }
                is NetworkState.Failure -> binding.root.showSnackbar(
                    getString(R.string.signup_error),
                    true
                )
                is NetworkState.Error -> binding.root.showSnackbar(
                    getString(R.string.network_error),
                    true
                )
            }
        }
    }

}