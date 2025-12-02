package com.example.jizhangbao.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.jizhangbao.R
import com.example.jizhangbao.database.entity.BillEntity
import com.example.jizhangbao.databinding.ItemBiliBinding
import com.google.android.material.color.DynamicColors
import com.google.android.material.color.DynamicColorsOptions
import com.google.android.material.color.HarmonizedColors
import com.google.android.material.color.HarmonizedColorsOptions
import com.google.android.material.color.MaterialColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.security.AccessController.getContext
import java.text.SimpleDateFormat


class BiliAdapter :
    ListAdapter<BillEntity, ViewHolder>(object : DiffUtil.ItemCallback<BillEntity>() {
        override fun areItemsTheSame(oldItem: BillEntity, newItem: BillEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BillEntity, newItem: BillEntity): Boolean {
            return oldItem.toString() == newItem.toString()
        }

    }) {

    var itemOnLongClickListener: ((BillEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBiliBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SimpleDateFormat", "ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding as ItemBiliBinding

        val data = getItem(position)
        binding.apply {

            val format = SimpleDateFormat("MM-dd HH:mm")
            date.text = format.format(data.date)
            money.text = data.money.toString()
            remark.text = data.remark



            iconLy.setBackgroundResource(R.color.md_theme_primaryContainer)
            icon.setImageResource(data.type.icon)

            root.setOnLongClickListener {
                MaterialAlertDialogBuilder(root.context)
                    .setTitle("操作")
                    .setMessage("确定要删除它吗？")
                    .setNeutralButton("手滑了") { dialog, which ->
                        dialog.cancel()
                    }
                    .setPositiveButton("确认删除") { dialog, which ->
                        dialog.cancel()
                        itemOnLongClickListener?.apply {
                            invoke(data)
                        }
                    }
                    .show()

                true
            }


        }


    }
}



