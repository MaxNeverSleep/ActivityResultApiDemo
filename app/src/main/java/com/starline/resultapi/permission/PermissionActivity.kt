package com.starline.resultapi.permission

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.starline.resultapi.PermissionUtils
import com.starline.resultapi.databinding.ActivityPermissionBinding

class PermissionActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ResultApi PermissionActivity"
        private const val onePermission = Manifest.permission.READ_CALENDAR
        private val multiPermission =
            arrayOf(Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_PHONE_STATE)
    }

    private lateinit var binding: ActivityPermissionBinding

    /**
     * 启动器
     */
    private val singlePermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { result ->
            //结果会接受在这个回调方法内
            Log.d(TAG, "result = $result")
            if (!result && PermissionUtils.isPermissionDeniedPermanently(this, onePermission)) {
                binding.tvSingleRequestDataContent.text = "denied permanently"
                Toast.makeText(
                    this,
                    "show dialog to ask user if want to jump to setting",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                binding.tvSingleRequestDataContent.text = result.toString()
            }
        }

    /**
     * 启动器
     */
    private val multiPermissionLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            //结果会接受在这个回调方法内
            Log.d(TAG, "result = $result")
            if (result[multiPermission[0]] == false && PermissionUtils.isPermissionDeniedPermanently(
                    this,
                    multiPermission[0]
                )
            ) {
                binding.tvMultiRequestDataContent.text = "denied permanently"
                Toast.makeText(
                    this,
                    "now should jump to setting",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                binding.tvMultiRequestDataContent.text = result.toString()
            }
        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOnePermission.setOnClickListener {
            singlePermissionLauncher.launch(onePermission)
        }

        binding.btnMultiPermission.setOnClickListener {
            multiPermissionLauncher.launch(multiPermission)
        }


    }


}