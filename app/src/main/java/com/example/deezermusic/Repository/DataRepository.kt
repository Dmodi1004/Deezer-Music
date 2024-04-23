package com.example.deezermusic.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.deezermusic.Models.MyData
import com.example.deezermusic.Retrofit.DeezerApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DataRepository @Inject constructor(private val deezerApi: DeezerApi) {

    private val _data = MutableLiveData<MyData>()
    val data: LiveData<MyData>
        get() = _data

    fun getData() {
        deezerApi.getData("eminem").enqueue(object : Callback<MyData> {
            override fun onResponse(call: Call<MyData>, response: Response<MyData>) {
                if (response.isSuccessful) {
                    _data.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<MyData>, t: Throwable) {
                // Handle failure
            }
        })
    }
}