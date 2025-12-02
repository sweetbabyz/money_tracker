package com.example.jizhangbao.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jizhangbao.R
import com.example.jizhangbao.adapter.MainPageAdapter
import com.example.jizhangbao.common.activity.BaseActivity
import com.example.jizhangbao.databinding.ActivityMainBinding
import com.example.jizhangbao.ui.home.BookFragment
import com.example.jizhangbao.ui.home.CommunityFragment
import com.example.jizhangbao.ui.home.IotFragmentJava
import com.example.jizhangbao.ui.home.UserFragment

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()

    }

    private fun initView() {

        initFragment()
    }

    private fun initFragment() {

        val mainPageAdapter = MainPageAdapter(
            listOf(
                BookFragment.newInstance(),
                CommunityFragment.newInstance(),
                UserFragment.newInstance()
            ),
            supportFragmentManager,
            lifecycle
        )

        binding.apply {
            mainVp2.adapter = mainPageAdapter
            mainVp2.isUserInputEnabled = false

            mainBottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.main_menu_home -> {
                        mainVp2.currentItem = 0
                    }

                    R.id.main_menu_community -> {
                        mainVp2.currentItem = 1
                    }

                    R.id.main_menu_person -> {
                        mainVp2.currentItem = 2
                    }
                }
                mainToolbar.title = it.title

                true
            }


        }

    }
}