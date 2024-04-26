package com.example.deezermusic.Retrofit

/*import com.example.deezermusic.Models.MyData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DeezerApi {
    @Headers(
        "X-RapidAPI-Key: 3de67f244cmshcc19b41f26fd0b5p15b520jsn5e3e994b2ed0",
        "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com"
    )
    @GET("search")
    fun getData(@Query("q") query: String): Call<MyData>
}*/

import com.example.deezermusic.Models.MyData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DeezerApi {
    @Headers(
        "X-RapidAPI-Key: 3de67f244cmshcc19b41f26fd0b5p15b520jsn5e3e994b2ed0",
        "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com"
    )
    @GET("search")
    suspend fun getData(@Query("q") query: String): Response<MyData>
}

