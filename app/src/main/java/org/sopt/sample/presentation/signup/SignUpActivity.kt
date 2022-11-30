package org.sopt.sample.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.presentation.login.LoginActivity
import org.sopt.sample.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private var check_et_email: String = ""
    private var check_et_pw: String = ""
    private var check_et_name: String = ""
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
            if (it.status >= 200 && it.status < 300) {
                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                setResult(AppCompatActivity.RESULT_OK, intent)
                if (!isFinishing) finish()
            }
        }
        viewModel.errorMessage.observe(this) {
            fail_signUp_toast(it)
        }
    }

    private fun fail_signUp_toast(errorMessage: String) {
        Toast.makeText(
            this@SignUpActivity,
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()
    }
    private val textWatcher = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            check_et_pw = binding.etPw.text.toString()
            binding.btnSignupFinish.isEnabled =
                check_et_email.isNotEmpty() && check_et_name.isNotEmpty() && check_et_pw.isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {}
    }
}