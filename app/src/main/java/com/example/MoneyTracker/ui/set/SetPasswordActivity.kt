package com.example.jizhangbao.ui.set

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.jizhangbao.R
import com.example.jizhangbao.common.activity.BaseActivity
import com.example.jizhangbao.common.application.App
import com.example.jizhangbao.common.utils.checkInput
import com.example.jizhangbao.databinding.ActivitySetPasswordBinding
import com.example.jizhangbao.ui.login.LoginActivity
import kotlinx.coroutines.launch

class SetPasswordActivity : BaseActivity() {

    private lateinit var binding: ActivitySetPasswordBinding

    private val userDao = App.thiz.jzbDatabase.userDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetPasswordBinding.inflate(layoutInflater)
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
            setSupportActionBar(mainToolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
        }


        initEnv()

    }

    private fun initEnv() {

        binding.apply {
            setPassword.setOnClickListener {

                if (checkInput(oldPassword)) {
                    Toast.makeText(this@SetPasswordActivity, "旧密码不可为空", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                if (checkInput(newPassword)) {
                    Toast.makeText(this@SetPasswordActivity, "新密码不可空", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                val sharedPreferences = getSharedPreferences("JZB_KV", Context.MODE_PRIVATE)

                lifecycleScope.launch {
                    val userEntity =
                        userDao.selectByUsername(sharedPreferences.getString("loginUser", "") ?: "")

                    userEntity?.let {

                        if (userEntity.password != oldPassword.text.toString()) {
                            Toast.makeText(
                                this@SetPasswordActivity,
                                "旧密码错误",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@let
                        }


                        userDao.update(userEntity.copy(password = newPassword.text.toString()))

                        Toast.makeText(
                            this@SetPasswordActivity,
                            "修改成功",
                            Toast.LENGTH_SHORT
                        ).show()
                        finishAllActivities()

                        Intent(this@SetPasswordActivity, LoginActivity::class.java).apply {
                            sharedPreferences.edit {
                                putString("loginUser", "")
                                putString("password", "")
                            }
                            startActivity(this)
                        }

                    }


                }

            }
        }
    }

}