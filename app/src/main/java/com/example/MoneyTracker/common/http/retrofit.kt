package com.example.jizhangbao.common.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val retrofit = Retrofit.Builder()
    .baseUrl("https://sspai.com/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build();