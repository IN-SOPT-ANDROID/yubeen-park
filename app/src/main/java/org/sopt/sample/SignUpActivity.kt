package org.sopt.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()

    }

    fun initListener() {
        //binding.btnSignupFinish.isEnabled = binding.etEmail.length() > 0 && binding.etPw.length() > 0

        binding.btnSignupFinish.setOnClickListener {
            //서버통신
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
                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    setResult(RESULT_OK, intent)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<ResponseSignup>, t: Throwable) {
                    Toast.makeText(this@SignUpActivity, "네트워크 에러", Toast.LENGTH_SHORT).show()
                    Log.e("SIGNUP FAIL", "mes : " +t.message)

                }
            })

        }
    }
}