package com.starline.resultapi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.starline.resultapi.custom.FirstActivity
import com.starline.resultapi.databinding.ActivityMainBinding
import com.starline.resultapi.media.MediaActivity
import com.starline.resultapi.permission.PermissionActivity

/**
 *
 * @author jianyu.yang
 * @date 2022/3/14
 **/
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnToCustom.setOnClickListener {
            startActivity(Intent(this@MainActivity, FirstActivity::class.java))
        }

        binding.btnToStartActivityForResult.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    com.starline.resultapi.start_activity_for_result.FirstActivity::class.java
                )
            )
        }

        binding.btnToPermission.setOnClickListener {
            startActivity(Intent(this@MainActivity, PermissionActivity::class.java))
        }

        binding.btnToMedia.setOnClickListener {
            startActivity(Intent(this@MainActivity, MediaActivity::class.java))
        }
    }


}