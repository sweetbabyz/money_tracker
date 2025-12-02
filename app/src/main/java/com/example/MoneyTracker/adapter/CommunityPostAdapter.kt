package com.example.jizhangbao.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.Headers
import com.example.jizhangbao.databinding.PostItemBinding
import com.example.jizhangbao.model.CommunityPostData
import com.example.jizhangbao.ui.post.PostInfoActivity

class CommunityPostAdapter : ListAdapter<CommunityPostData.Data, ViewHolder>(object :
    DiffUtil.ItemCallback<CommunityPostData.Data>() {
    override fun areItemsTheSame(
        oldItem: CommunityPostData.Data,
        newItem: CommunityPostData.Data
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CommunityPostData.Data,
        newItem: CommunityPostData.Data
    ): Boolean {
        return oldItem.title == newItem.title
    }


}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        (holder.binding as PostItemBinding).apply {

            val imageUrl =
                "https://cdnfile.sspai.com/${data.banner}?imageMogr2/auto-orient/thumbnail/!284x142r/gravity/center/crop/284x142/format/webp/ignore-error/1"
            Log.d("图标", "onBindViewHolder: $imageUrl")
            val gliderUrl = GlideUrl(imageUrl,
                Headers {
                    return@Headers mapOf(
                        "User-Agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36 Edg/125.0.0.0",
                        "Referer" to "https://sspai.com/"
                    )
                })

            Glide.with(face).load(gliderUrl).into(face)
            title.text = data.title.replace("<em>", "").replace("</em>", "")
            user.text = data.author.nickname
            doc.text = data.summary.replace("<em>", "").replace("</em>", "")

            card.setOnClickListener {
                Intent(root.context, PostInfoActivity::class.java).let {
                    it.putExtra("postId", data.id)
                    root.context.startActivity(it)
                }
            }
        }
    }
}