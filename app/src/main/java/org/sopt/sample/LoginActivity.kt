package org.sopt.sample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.data.remote.RequestLogin
import org.sopt.sample.data.remote.ResponseLogin
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private val loginService = ServicePool.authService
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

    private fun fail_login_toast() {
        Toast.makeText(
            this@LoginActivity,
            getString(R.string.login_error),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun initListener() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
        binding.btnLogin.setOnClickListener {
            loginService.login(
                RequestLogin(
                    binding.etEmail.text.toString(),
                    binding.etPw.text.toString()
                )
            ).enqueue(object : Callback<ResponseLogin> {
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    if (response.isSuccessful) {
                        binding.etEmail.text = null
                        binding.etPw.text = null
                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        fail_login_toast()
                    }

                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    fail_login_toast()
                    Log.e("SIGN IN FAIL", "mes : " + t.message)
                }
            })


        }
    }
}