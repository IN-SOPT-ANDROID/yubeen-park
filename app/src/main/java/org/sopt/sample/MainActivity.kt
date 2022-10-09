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
    private lateinit var id : String
    private lateinit var pw : String
    private lateinit var mbti : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)//조상클래스에 있는 oncreate 함수 호출
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)//findviewbyid로 접근해야 되는데
        //binding.txtMainIdTitle.text = "Hello World!"

        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                id = result.data?.getStringExtra("id").toString()
                pw = result.data?.getStringExtra("pw").toString()
                mbti = result.data?.getStringExtra("mbti").toString()
                Snackbar.make(binding.root, "회원가입이 완료되었습니다", Snackbar.LENGTH_SHORT).show()
            }
        }
        init()

    }

    fun init() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
        binding.btnLogin.setOnClickListener {

            if (binding.etId.text.length < 6 || binding.etId.text.length > 10) {
                Snackbar.make(binding.root, getString(R.string.main_error_1), Snackbar.LENGTH_SHORT)
                    .setAnchorView(binding.etPw).show()
                return@setOnClickListener
            } else {
                val len = binding.etPw.text.length
                if (len < 8 || len > 12) {
                    Snackbar.make(binding.root, getString(R.string.main_error_2), Snackbar.LENGTH_SHORT)
                        .setAnchorView(binding.etPw).show()
                    return@setOnClickListener
                }
            }

            if (id == binding.etId.text.toString() && pw == binding.etPw.text.toString()) {//로그인 성공
                Toast.makeText(this, R.string.main_txt_1, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java).apply {
                    putExtra("id", id)
                    putExtra("pw", pw)
                    putExtra("mbti", mbti)
                }

                startActivity(intent)
            }

        }
    }
}