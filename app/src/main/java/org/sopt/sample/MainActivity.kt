package org.sopt.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)//조상클래스에 있는 oncreate 함수 호출
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)//findviewbyid로 접근해야 되는데

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

    fun initListener() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
        binding.btnLogin.setOnClickListener {


//            if (id == binding.etId.text.toString() && pw == binding.etPw.text.toString()) {//로그인 성공
//                Toast.makeText(this, getString(R.string.main_login_success), Toast.LENGTH_SHORT)
//                    .show()
//                val intent = Intent(this, HomeActivity::class.java).apply {
//                    putExtra("id", id)
//                    putExtra("pw", pw)
//                    putExtra("mbti", mbti)
//                }
//
//                startActivity(intent)
//            }

        }
    }
}