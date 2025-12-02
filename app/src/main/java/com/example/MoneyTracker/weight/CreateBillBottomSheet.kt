package com.example.jizhangbao.weight

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.jizhangbao.R
import com.example.jizhangbao.common.application.App
import com.example.jizhangbao.database.entity.BillEntity
import com.example.jizhangbao.databinding.CreateBiliDialogBinding
import com.example.jizhangbao.model.RecordType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import java.text.SimpleDateFormat
import java.util.Date


class CreateBillBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: CreateBiliDialogBinding

    var onSubmit: ((BillEntity) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = CreateBiliDialogBinding.inflate(layoutInflater)

        initView()

        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    private fun initView() {

        var mDate = Date(System.currentTimeMillis())

        val format = SimpleDateFormat("yyyy-MM-dd")

        binding.apply {

            val types = RecordType.entries.map { it.title }
            (typeInput as? MaterialAutoCompleteTextView)?.apply {
                setText("其他")
                setSimpleItems(types.toTypedArray())
            }


            date.setText(format.format(mDate))

            date.setOnClickListener {
                val datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("选择日期")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()
                datePicker.show(requireActivity().supportFragmentManager, "DateSelect")
                datePicker.addOnPositiveButtonClickListener {
                    datePicker.selection?.also {
                        mDate = Date(it)
                        date.setText(format.format(mDate))
                    }
                }
            }

            postButton.setOnClickListener {
                dismiss()
                date.setText(format.format(mDate))
                onSubmit?.apply {
                    invoke(
                        BillEntity(
                            userId = App.loginUserId,
                            money = money.text.toString().toDouble(),
                            remark = remark.text.toString(),
                            type = RecordType.entries.find { it.title == typeInput.text.toString() }!!,
                            date = mDate
                        )
                    )
                }
            }


        }

    }
}