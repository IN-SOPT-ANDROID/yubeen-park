package org.sopt.sample.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.R
import org.sopt.sample.data.remote.AuthNetworkState
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.presentation.login.LoginActivity

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
                is AuthNetworkState.Success -> {
                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    setResult(RESULT_OK, intent)
                    if (!isFinishing) finish()
                }
                is AuthNetworkState.Failure -> failSignupSnackbar(getString(R.string.signup_error))
                is AuthNetworkState.Error -> failSignupSnackbar(getString(R.string.network_error))
            }
        }
    }

    private fun failSignupSnackbar(errorMessage: String) {
        Snackbar.make(
            binding.root,
            errorMessage,
            Snackbar.LENGTH_SHORT
        ).show()
    }

}