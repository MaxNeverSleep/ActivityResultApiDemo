package com.starline.resultapi.media

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.starline.resultapi.databinding.ActivityMediaBinding
import java.io.File
import android.graphics.ImageDecoder
import androidx.core.content.FileProvider


class MediaActivity : AppCompatActivity() {

    private var uri: Uri? = null

    private val binding: ActivityMediaBinding by lazy {
        ActivityMediaBinding.inflate(layoutInflater)
    }

    /**
     * 拍摄照片启动器
     */
    private val takePhotoLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { result ->
            //直接返回是否拍摄成功
            if (result) {
                //使用启动时传入的uri获取图片
                val source = ImageDecoder.createSource(this.contentResolver, uri!!)
                val bitmap = ImageDecoder.decodeBitmap(source)
                binding.ivResult.setImageBitmap(
                    bitmap
                )
            }
        }


    /**
     * 拍摄preview照片(低清图片，占用内存较小)启动器
     */
    private val takePhotoPreviewLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { result ->
            //直接返回拍摄的bitmap
            binding.ivResult.setImageBitmap(
                result
            )
        }


    /**
     * 拍摄视频
     */
    private val takeVideoLauncher =
        registerForActivityResult(ActivityResultContracts.TakeVideo()) { result ->
            //bitmap类型的结果，预览图，目前返回会为null
            binding.ivResult.setImageBitmap(
                result
            )
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnTakePhoto.setOnClickListener {
            val file =
                File(cacheDir.absolutePath + "/" + System.currentTimeMillis() + ".jpg")
            uri =
                FileProvider.getUriForFile(this, applicationContext.packageName + ".provider", file)
            takePhotoLauncher.launch(uri)
        }

        binding.btnTakePhotoPreview.setOnClickListener {
            takePhotoPreviewLauncher.launch(null)
        }

        binding.btnTakeVideo.setOnClickListener {
            val file =
                File(cacheDir.absolutePath + "/" + System.currentTimeMillis() + ".mp4")
            uri =
                FileProvider.getUriForFile(this, applicationContext.packageName + ".provider", file)
            takeVideoLauncher.launch(uri)

        }

    }

}