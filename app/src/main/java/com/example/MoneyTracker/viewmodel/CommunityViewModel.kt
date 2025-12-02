package com.example.jizhangbao.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jizhangbao.common.http.retrofit
import com.example.jizhangbao.model.CommunityPostData
import com.example.jizhangbao.network.CommunityApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommunityViewModel : ViewModel() {

    private val _postList = MutableStateFlow<List<CommunityPostData.Data>>(emptyList())
    val postList = _postList.asStateFlow()
    private val communityApi = retrofit.create(CommunityApi::class.java)

    fun getCommunityInfo(
        title: String = "记账日记",
        offset: Int = 1,
        limit: Int = 8
    ) {
        viewModelScope.launch {
            val communityPostData = communityApi.getCommunityInfo(
                title = title,
                offset = offset,
                limit = limit
            )
            Log.d("响应了", "getCommunityInfo: $communityPostData")
            _postList.value = communityPostData.data
        }
    }

}