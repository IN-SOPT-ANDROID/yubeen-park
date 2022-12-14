package org.sopt.sample.presentation.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.sample.R
import org.sopt.sample.data.state.NetworkState
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.presentation.login.LoginActivity
import org.sopt.sample.util.showSnackbar

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        initListener()
    }

    private fun initListener() {
        //서버통신
        binding.btnSignupFinish.setOnClickListener {
            viewModel.signUp(
                binding.etEmail.text.toString(),
                binding.etPw.text.toString(),
                binding.etName.text.toString()
            )
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