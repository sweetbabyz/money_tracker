package com.example.jizhangbao.ui.post

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LevelListDrawable
import android.os.Bundle
import android.text.Html
import android.text.Html.ImageGetter
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.jizhangbao.R
import com.example.jizhangbao.common.http.retrofit
import com.example.jizhangbao.databinding.ActivityPostInfoBinding
import com.example.jizhangbao.network.CommunityApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL


class PostInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostInfoBinding
    private val communityApi = retrofit.create(CommunityApi::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostInfoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView() {
        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)

            //视频初始化
            video.setVideoPath("https://www.w3school.com.cn/example/html5/mov_bbb.mp4")
            video.requestFocus()
            video.start()
        }
        initData()
        initEnv()


    }

    private fun initData() {

        lifecycleScope.launch {
            val postId = intent.getIntExtra("postId", 53211)
            val postInfo = communityApi.getPostInfo(id = postId)
            withContext(Dispatchers.IO) { // 确保在后台线程上执行网络请求
                binding.apply {
                    launch(Dispatchers.Main) { toolbar.title = postInfo.data.title }
                    val spanned = Html.fromHtml(
                        postInfo.data.body,
                        Html.FROM_HTML_MODE_COMPACT,
                        { source ->
                            try {
                                val bitmap =
                                    BitmapFactory.decodeStream(URL(source).openStream())
                                val drawable = BitmapDrawable(bitmap)
                                drawable.setBounds(
                                    0,
                                    0,
                                    bitmap.getWidth() - 1,
                                    bitmap.getHeight() - 1
                                )
                                drawable
                            } catch (e: Exception) {
                                e.printStackTrace()
                                null
                            }
                        },
                        null
                    )
                    launch(Dispatchers.Main) {
                        body.text = spanned
                    }
                }
            }
        }


    }


    private fun initEnv() {
        binding.apply {

        }
    }
}