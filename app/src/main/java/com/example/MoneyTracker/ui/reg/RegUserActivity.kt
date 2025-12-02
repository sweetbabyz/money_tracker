package com.example.jizhangbao.ui.reg

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.jizhangbao.R
import com.example.jizhangbao.common.activity.BaseActivity
import com.example.jizhangbao.common.application.App
import com.example.jizhangbao.common.utils.checkInput
import com.example.jizhangbao.database.entity.UserEntity
import com.example.jizhangbao.databinding.ActivityRegUserBinding
import com.example.jizhangbao.ui.login.LoginActivity
import com.example.jizhangbao.viewmodel.RegUserResult
import com.example.jizhangbao.viewmodel.RegViewMode
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch


class RegUserActivity : BaseActivity() {
    private lateinit var binding: ActivityRegUserBinding
    private val viewModel by viewModels<RegViewMode>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegUserBinding.inflate(layoutInflater)
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
        }
        initEnv()
        initFlow()
    }

    private fun initFlow() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.regUserResult.collect {
                    when (it) {
                        is RegUserResult.Failure -> {
                            Toast.makeText(this@RegUserActivity, it.msg, Toast.LENGTH_SHORT).show()
                        }

                        RegUserResult.Idle -> {}
                        RegUserResult.Success -> {
                            Toast.makeText(this@RegUserActivity, "注册成功", Toast.LENGTH_SHORT)
                                .show()
                            Intent(this@RegUserActivity, LoginActivity::class.java).apply {
                                putExtra("username", binding.usernameInput.text.toString())
                                putExtra("password", binding.passwordInput.text.toString())
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

            toLogin.setOnClickListener {
                Intent(this@RegUserActivity, LoginActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }

            regButton.setOnClickListener {
                if (checkInput(usernameInput)) {
                    Toast.makeText(this@RegUserActivity, "用户名不可空", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (checkInput(passwordInput) || checkInput(twoPasswordInput)) {
                    Toast.makeText(this@RegUserActivity, "密码不可空", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (passwordInput.text.toString() != twoPasswordInput.text.toString()) {
                    Toast.makeText(this@RegUserActivity, "两次密码不一致", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                viewModel.regUser(
                    UserEntity(
                        username = usernameInput.text.toString(),
                        password = passwordInput.text.toString()
                    )
                )
            }


        }
    }


}