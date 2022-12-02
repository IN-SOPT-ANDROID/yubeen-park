package org.sopt.sample.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.R
import org.sopt.sample.data.remote.AuthNetworkState
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.presentation.login.LoginActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private var checkEtEmail: String = ""
    private var checkEtPw: String = ""
    private var checkEtName: String = ""
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()

    }

    private fun initListener() {
        //모든 입력창에 입력값이 있는 경우에만 버튼 활성화
        binding.etEmail.addTextChangedListener(textWatcher)
        binding.etName.addTextChangedListener(textWatcher)
        binding.etPw.addTextChangedListener(textWatcher)


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
                AuthNetworkState.Success -> {
                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    setResult(RESULT_OK, intent)
                    if (!isFinishing) finish()
                }
                AuthNetworkState.Failure -> failSignupSnackbar(getString(R.string.signup_error))
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

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            checkEtPw = binding.etPw.text.toString()
            checkEtEmail = binding.etEmail.text.toString()
            checkEtName = binding.etName.text.toString()
            binding.btnSignupFinish.isEnabled =
                checkEtEmail.isNotEmpty() && checkEtName.isNotEmpty() && checkEtPw.isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {}
    }
}