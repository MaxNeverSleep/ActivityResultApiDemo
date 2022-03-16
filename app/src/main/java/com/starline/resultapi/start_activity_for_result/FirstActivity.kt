package com.starline.resultapi.start_activity_for_result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.starline.resultapi.databinding.ActivityStartBinding

class FirstActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ResultApi FirstActivity"
    }

    private lateinit var binding: ActivityStartBinding

    /**
     * 启动器
     */
    private val launcher: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            //结果会接受在这个回调方法内
            Log.d(TAG, "result = $result")
            binding.tvRequestDataContent.text = result.data?.getStringExtra("result")
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLaunch.setOnClickListener {
            //启动SecondActivity
            launcher.launch(Intent(this@FirstActivity, SecondActivity::class.java).apply {
                putExtra(
                    "name",
                    "Max"
                )
            })
        }

    }
}