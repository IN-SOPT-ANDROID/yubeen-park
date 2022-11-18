package org.sopt.sample

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.data.remote.RequestSignup
import org.sopt.sample.data.remote.ResponseSignup
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val signUpService = ServicePool.authService
    private var check_et_email: String = ""
    private var check_et_pw: String = ""
    private var check_et_name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()

    }

    private fun initListener() {
        //모든 입력창에 입력값이 있는 경우에만 버튼 활성화
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                check_et_email = binding.etEmail.text.toString()
                binding.btnSignupFinish.isEnabled =
                    check_et_email.isNotEmpty() && check_et_name.isNotEmpty() && check_et_pw.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                check_et_name = binding.etName.text.toString()
                binding.btnSignupFinish.isEnabled =
                    check_et_email.isNotEmpty() && check_et_name.isNotEmpty() && check_et_pw.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.etPw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                check_et_pw = binding.etPw.text.toString()
                binding.btnSignupFinish.isEnabled =
                    check_et_email.isNotEmpty() && check_et_name.isNotEmpty() && check_et_pw.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })


        //서버통신
        binding.btnSignupFinish.setOnClickListener {
            signUpService.signup(
                RequestSignup(
                    binding.etEmail.text.toString(),
                    binding.etPw.text.toString(),
                    binding.etName.text.toString()
                )
            ).enqueue(object : Callback<ResponseSignup> {
                override fun onResponse(
                    call: Call<ResponseSignup>,
                    response: Response<ResponseSignup>
                ) {
                    if (response.isSuccessful) {
                        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                        setResult(RESULT_OK, intent)
                        if (!isFinishing) finish()
                    } else {
                        Toast.makeText(
                            this@SignUpActivity,
                            getString(R.string.signup_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

                override fun onFailure(call: Call<ResponseSignup>, t: Throwable) {
                    Toast.makeText(
                        this@SignUpActivity,
                        getString(R.string.network_error),
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("SIGNUP FAIL", "mes : " + t.message)

                }
            })

        }
    }
}