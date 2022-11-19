package org.sopt.sample.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.HomeActivity
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityLoginBinding
import org.sopt.sample.presentation.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.signup_finish),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        initListener()

    }

    private fun fail_login_toast(errorMessage: String) {
        Toast.makeText(
            this@LoginActivity,
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()
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
            if (it.status >= 200 && it.status < 300) {
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        }

        viewModel.errorMessage.observe(this) {
            fail_login_toast(it)
        }
    }
}