package org.sopt.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var id : String
    private lateinit var mbti : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("id")){
            binding.mainId.text = binding.mainId.text.toString() + intent.getStringExtra("id").toString()
        }

        if(intent.hasExtra("mbti")){
            binding.mainMbti.text = binding.mainMbti.text.toString() + intent.getStringExtra("mbti").toString()
        }
    }
}