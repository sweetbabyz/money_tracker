package com.example.jizhangbao.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.jizhangbao.common.application.App
import com.example.jizhangbao.databinding.FragmentUserBinding
import com.example.jizhangbao.ui.login.LoginActivity
import com.example.jizhangbao.ui.set.SetPasswordActivity
import kotlinx.coroutines.launch


class UserFragment : Fragment() {


    private lateinit var binding: FragmentUserBinding
    private val userDao = App.thiz.jzbDatabase.userDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentUserBinding.inflate(layoutInflater, container, false)

        initView()

        return binding.root
    }

    private fun initView() {
        loadUserInfo()
        initEnv()
    }

    private fun initEnv() {
        binding.apply {
            loginout.setOnClickListener {
                val sharedPreferences =
                    requireActivity().getSharedPreferences("JZB_KV", Context.MODE_PRIVATE)
                sharedPreferences.edit {
                    putString("loginUser", "")
                }
                Intent(requireActivity(), LoginActivity::class.java).apply {
                    startActivity(this)
                    requireActivity().finish()
                }
            }

            setPassword.setOnClickListener {
                Intent(requireActivity(), SetPasswordActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }

    private fun loadUserInfo() {
        val sharedPreferences =
            requireActivity().getSharedPreferences("JZB_KV", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("loginUser", "") ?: ""
        lifecycleScope.launch {
            val userEntity = userDao.selectByUsername(username)
            binding.username.text = userEntity?.username
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            UserFragment()
    }
}