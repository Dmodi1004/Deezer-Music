package com.example.deezermusic.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.deezermusic.Models.Data
import com.example.deezermusic.Retrofit.DeezerApi
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class DeezerPagingSource(
    private val deezerApi: DeezerApi,
    private val query: String
) : PagingSource<Int, Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = deezerApi.getData(query)

            if (response.isSuccessful) {
                val data = response.body()?.data ?: emptyList()
                LoadResult.Page(
                    data = data,
                    prevKey = null,
                    nextKey = null
                )
            } else {
                LoadResult.Error(Exception("Failed to load data"))
            }
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
