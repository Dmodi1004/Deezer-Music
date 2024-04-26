package com.example.deezermusic.Repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.deezermusic.Retrofit.DeezerApi
import com.example.deezermusic.paging.DeezerPagingSource
import javax.inject.Inject

/*
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
}*/

class DataRepository @Inject constructor(private val deezerApi: DeezerApi) {

    fun getData(query: String) = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { DeezerPagingSource(deezerApi, query) }
    ).liveData

}
