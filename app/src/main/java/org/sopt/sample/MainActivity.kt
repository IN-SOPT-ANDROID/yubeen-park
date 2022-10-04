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
    private var id = ""
    private var pw = ""
    private var mbti = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)//조상클래스에 있는 oncreate 함수 호출
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)//findviewbyid로 접근해야 되는데
        //binding.txtMainIdTitle.text = "Hello World!"

        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
                result -> if(result.resultCode == RESULT_OK){
            id = result.data?.getStringExtra("id").toString()
            pw = result.data?.getStringExtra("pw").toString()
            mbti = result.data?.getStringExtra("mbti").toString()
            Snackbar.make(binding.root, "회원가입이 완료되었다", Snackbar.LENGTH_SHORT).show()
        }
        }
        init()

    }

    fun init(){
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
        binding.btnLogin.setOnClickListener {

            if(binding.etId.text.length < 6 || binding.etId.text.length > 10){
                Snackbar.make(binding.root, "아이디가 잘못되었습니다", Snackbar.LENGTH_SHORT).setAnchorView(binding.etPw).show()
                return@setOnClickListener
            }
            else{
                val len = binding.etPw.text.length
                if(len < 8 || len > 12){
                    Snackbar.make(binding.root, "패스워드가 잘못 되었습니다", Snackbar.LENGTH_SHORT).setAnchorView(binding.etPw).show()
                    return@setOnClickListener
                }
            }

            if(id == binding.etId.text.toString() && pw == binding.etPw.text.toString()){//로그인 성공
                Toast.makeText(this, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("pw", pw)
                intent.putExtra("mbti", mbti)
                startActivity(intent)
            }
//            val intent = Intent(this, SampleActivity::class.java)
//            startActivity(intent)

        }
    }
}