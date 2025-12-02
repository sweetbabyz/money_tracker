package com.example.jizhangbao.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jizhangbao.adapter.BiliAdapter
import com.example.jizhangbao.adapter.BiliDateAdapter
import com.example.jizhangbao.common.application.App
import com.example.jizhangbao.databinding.FragmentBookBinding
import com.example.jizhangbao.viewmodel.BookFragmentViewModel
import com.example.jizhangbao.weight.CreateBillBottomSheet
import kotlinx.coroutines.launch


class BookFragment : Fragment() {

    private lateinit var binding: FragmentBookBinding

    private val viewModel by viewModels<BookFragmentViewModel>()

    private val biliAdapter = BiliAdapter()
    private val biliDateAdapter = BiliDateAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBookBinding.inflate(layoutInflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.rv.adapter = biliAdapter
        binding.rv.layoutManager = LinearLayoutManager(requireContext())

        binding.dateRv.adapter = biliDateAdapter
        binding.dateRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        biliDateAdapter.onSelectDate = {
            lifecycleScope.launch {
                viewModel.updateBillData(it)
            }
        }

        initEnv()

        initFlow()
        initData()


    }

    private fun initEnv() {

        binding.apply {
            addButton.setOnClickListener {
                val createBillBottomSheet = CreateBillBottomSheet()

                createBillBottomSheet.onSubmit = {
                    viewModel.addBill(it)
                }
                createBillBottomSheet.show(requireActivity().supportFragmentManager, "CreateBill")
            }

            biliAdapter.itemOnLongClickListener = {
                viewModel.deleteById(it)
            }
        }

    }

    private fun initData() {
        lifecycleScope.launch {
            viewModel.initDateInfo()
            viewModel.updateBillData()
        }
    }

    private fun initFlow() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.biliDatesFlow
                    .collect {
                        var totalMoney = 0.0
                        it.forEach { item ->
                            totalMoney += item.money
                        }
                        biliDateAdapter.totalMoney = totalMoney
                        biliDateAdapter.biliDates = it
                        biliDateAdapter.notifyDataSetChanged()
                        binding.dateRv.post {
                            binding.dateRv.scrollToPosition(it.size - 1)
                        }
                    }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.biliFlow
                    .collect {
                        var totalMoney = 0.0
                        it.forEach { item ->
                            totalMoney += item.money
                        }
                        binding.totalMoney.text = "￥$totalMoney"
                        biliAdapter.submitList(it)
                    }
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            BookFragment()
    }
}