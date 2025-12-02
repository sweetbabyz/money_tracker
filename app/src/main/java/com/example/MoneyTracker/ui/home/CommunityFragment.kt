package com.example.jizhangbao.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jizhangbao.adapter.CommunityPostAdapter
import com.example.jizhangbao.databinding.FragmentCommunityBinding
import com.example.jizhangbao.viewmodel.CommunityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommunityFragment : Fragment() {

    private lateinit var binding: FragmentCommunityBinding
    private val viewModel by viewModels<CommunityViewModel>()
    private val postAdapter = CommunityPostAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommunityBinding.inflate(getLayoutInflater())
        initView()
        return binding.root
    }

    private fun initView() {
        binding.apply {
            rv.adapter = postAdapter
            rv.layoutManager = LinearLayoutManager(requireContext())
        }
        initFlow()

        viewModel.getCommunityInfo()
    }

    private fun initFlow() {

        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.postList.collect {
                    if (it.isEmpty()) {
                        binding.rv.visibility = View.GONE
                        binding.progressLy.visibility = View.VISIBLE
                    } else {
                        binding.rv.visibility = View.VISIBLE
                        binding.progressLy.visibility = View.GONE
                        postAdapter.submitList(it)
                    }
                }
            }
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CommunityFragment()
    }
}