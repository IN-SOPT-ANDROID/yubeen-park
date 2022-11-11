package org.sopt.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.data.remote.ServicePool
import org.sopt.sample.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val signUpService = ServicePool
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()

    }

    fun initListener() {
        binding.btnSignupFinish.isEnabled = binding.etId.length() > 0 && binding.etPw.length() > 0

        binding.btnSignupFinish.setOnClickListener {
            //서버통신

            //회원가입 성공
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("id", binding.etId.text.toString())
                putExtra("pw", binding.etPw.text.toString())
                putExtra("mbti", binding.etMbti.text.toString())
            }
            setResult(RESULT_OK, intent)
            if (!isFinishing) finish()

        }
    }
}