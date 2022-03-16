package com.starline.resultapi.custom

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import com.starline.resultapi.databinding.ActivityStartBinding

class FirstActivity : AppCompatActivity() {

    /**
     * 自定义协议类
     */
    class MyActivityResultContract :
        ActivityResultContract<String?, String?>() {

        //创建启动SecondActivity的Intent
        override fun createIntent(context: Context, input: String?): Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("name", input)
            return intent
        }

        //解析从SecondActivity返回的Intent
        override fun parseResult(resultCode: Int, intent: Intent?): String? {
            val data: String?
            //接收到返回的Intent 得到出String类型结果做为返回
            if (intent != null) {
                data = intent.getStringExtra("result")
                if (resultCode == Activity.RESULT_OK && data != null) {
                    return data
                }
            }
            return null
        }
    }

    /**
     * 启动器
     */
    val launcher: ActivityResultLauncher<String> =
        registerForActivityResult<String, String>(
            MyActivityResultContract()
        ) { result ->
            //结果会接受在这个回调方法内
            Log.d(TAG, "result = $result")
            binding.tvRequestDataContent.text = result
        }


    companion object {
        private const val TAG = "ResultApi FirstActivity"
    }

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLaunch.setOnClickListener {
            //启动SecondActivity
            launcher.launch("Max")
        }

    }
}