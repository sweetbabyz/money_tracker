package com.example.jizhangbao.network

import com.example.jizhangbao.model.CommunityPostData
import com.example.jizhangbao.model.PostInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface CommunityApi {

    @GET("v1/search/article/page/get")
    suspend fun getCommunityInfo(
        @Query("free") free: Int = 1,
        @Query("title") title: String = "记账日记",
        @Query("offset") offset: Int = 1,
        @Query("limit") limit: Int = 8
    ): CommunityPostData

    @GET("v1/article/info/get")
    suspend fun getPostInfo(
        @Query("id") id: Int = 53211,
        @Query("view") view: String = "second",
        @Query("support_webp") supportWebp: Boolean = true,
    ): PostInfo

}