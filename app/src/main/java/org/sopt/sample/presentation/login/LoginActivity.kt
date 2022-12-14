package org.sopt.sample.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.R
import org.sopt.sample.data.remote.NetworkState
import org.sopt.sample.databinding.ActivityLoginBinding
import org.sopt.sample.presentation.home.HomeActivity
import org.sopt.sample.presentation.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                failLoginSnackbar(getString(R.string.signup_finish))
            }
        }
        initListener()
    }

    private fun failLoginSnackbar(errorMessage: String) {
        Snackbar.make(
            binding.root,
            errorMessage,
            Snackbar.LENGTH_SHORT
        ).apply {
            anchorView = binding.btnLogin
        }.show()
    }

    private fun initListener() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.etEmail.text.toString(),
                binding.etPw.text.toString()
            )
        }

        //this->lifeCycleOwner, fragment면 뷰와 생명주기가 달라서->viewLifeCycleOwner제공
        viewModel.loginResult.observe(this) {
            when (it) {
                is NetworkState.Success -> {
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                }
                is NetworkState.Failure -> failLoginSnackbar(getString(R.string.login_error))
                is NetworkState.Error -> failLoginSnackbar(getString(R.string.network_error))
            }
        }
    }
}