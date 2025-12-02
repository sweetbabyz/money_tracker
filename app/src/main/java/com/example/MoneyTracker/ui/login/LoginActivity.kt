package com.example.jizhangbao.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.jizhangbao.R
import com.example.jizhangbao.common.activity.BaseActivity
import com.example.jizhangbao.common.application.App
import com.example.jizhangbao.common.utils.checkInput
import com.example.jizhangbao.databinding.ActivityLoginBinding
import com.example.jizhangbao.ui.MainActivity
import com.example.jizhangbao.ui.reg.RegUserActivity
import com.example.jizhangbao.viewmodel.BookFragmentViewModel
import com.example.jizhangbao.viewmodel.LoginResult
import com.example.jizhangbao.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val viewmodel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sharedPreferences = getSharedPreferences("JZB_KV", Context.MODE_PRIVATE)

        initView()
    }

    private fun initView() {

        if (!sharedPreferences.getString("loginUser", "").isNullOrBlank()) {
            // 已经登录
            val userId = sharedPreferences.getInt("loginUserId", 0)
            App.loginUserId = userId

            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }

        binding.apply {

            sharedPreferences.apply {
                val savePassword = getBoolean("savePassword", false)
                usernameInput.setText(getString("username", ""))
                passwordInput.setText(getString("password", ""))
                saveCheck.isChecked = savePassword
            }

            if (intent != null) {
                val username = intent.getStringExtra("username")
                val password = intent.getStringExtra("password")
                usernameInput.setText(username)
                passwordInput.setText(password)
            }

        }

        initEnv()
        initFlow()
    }

    private fun initFlow() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.loginUserInfo.collect {
                    when (it) {
                        LoginResult.Failure -> {
                            Toast.makeText(
                                this@LoginActivity,
                                "登录失败，检查用户名密码",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        LoginResult.Idle -> {}
                        is LoginResult.Success -> {
                            App.loginUserId = it.userEntity.id
                            Toast.makeText(this@LoginActivity, "登录成功", Toast.LENGTH_SHORT)
                                .show()

                            sharedPreferences.edit {
                                putString("loginUser", it.userEntity.username)
                                putInt("loginUserId", it.userEntity.id)
                            }

                            Intent(this@LoginActivity, MainActivity::class.java).apply {
                                startActivity(this)
                                finish()
                            }
                        }
                    }
                }
            }

        }
    }

    private fun initEnv() {
        binding.apply {
            login.setOnClickListener {
                if (checkInput(usernameInput)) {
                    Toast.makeText(this@LoginActivity, "用户名不可空", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                if (checkInput(passwordInput)) {
                    Toast.makeText(this@LoginActivity, "密码不可空", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (saveCheck.isChecked) {
                    sharedPreferences.edit {
                        putString("username", usernameInput.text.toString())
                        putString("password", passwordInput.text.toString())
                        putBoolean("savePassword", true)
                    }
                } else {
                    sharedPreferences.edit {
                        putString("username", "")
                        putString("password", "")
                        putBoolean("savePassword", false)
                    }
                }

                viewmodel.loginUser(usernameInput.text.toString(), passwordInput.text.toString())
            }

            toReg.setOnClickListener {
                Intent(this@LoginActivity, RegUserActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }


}