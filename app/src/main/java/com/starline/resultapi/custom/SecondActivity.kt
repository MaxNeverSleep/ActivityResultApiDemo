package com.starline.resultapi.custom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.starline.resultapi.databinding.ActivityEndBinding

class SecondActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ResultApi SecondActivity"
    }

    private lateinit var binding: ActivityEndBinding
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEndBinding.inflate(layoutInflater)
        setContentView(binding.root)

        name = intent.getStringExtra("name")
        Log.d(TAG, "getIntentExtra name = $name")

        binding.tvRequestDataContent.text = name

        binding.btnBack.setOnClickListener {
            val resultIntent = Intent().apply { putExtra("result", "$name is Handsome") }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}