package org.sopt.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }

    fun init() {
        binding.btnSignupFinish.setOnClickListener {
            if (binding.etId.text.length !in 6..10) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.main_id_error),
                    Snackbar.LENGTH_SHORT
                )
                    .setAnchorView(binding.etPw).show()
                return@setOnClickListener
            } else {
                val len = binding.etPw.text.length
                if (len !in 8..12) {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.main_pw_error),
                        Snackbar.LENGTH_SHORT
                    )
                        .setAnchorView(binding.etPw).show()
                    return@setOnClickListener
                }
            }
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