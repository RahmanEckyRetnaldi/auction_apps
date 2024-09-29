package com.rer.core.network.utils

import com.rer.core.utils.NativeLib
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitHolder @Inject constructor(
    httpClient: OkHttpClient,
) {
    val internalRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(NativeLib().getDevBaseUrl())
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}