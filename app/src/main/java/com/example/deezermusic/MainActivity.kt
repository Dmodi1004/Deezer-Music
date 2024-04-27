package com.example.deezermusic

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.deezermusic.Adapters.MyAdapter
import com.example.deezermusic.ViewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/*
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.deezermusic.Adapters.MyAdapter
import com.example.deezermusic.ViewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var loading: LottieAnimationView
    private lateinit var adapter: MyAdapter

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        loading = findViewById(R.id.loading)

        mediaPlayer = MediaPlayer()

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.dataLiveData.observe(this) { response ->
            loading.visibility = View.GONE
            adapter = MyAdapter(this, response.data, mediaPlayer)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            Log.d("TAG", "onResponse: $response")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    override fun onDestroy() {
        mediaPlayer.release()
        super.onDestroy()
    }

}*/

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private lateinit var viewModel: MainViewModel

    private lateinit var loading: LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        loading = findViewById(R.id.loading)

        adapter = MyAdapter()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        loading.visibility = View.VISIBLE

        viewModel.getData("eminem").observe(this) { pagingData ->
            if (pagingData != null) {
                adapter.submitData(lifecycle, pagingData)
            }
        }

        adapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.Loading) {
                loading.visibility = View.VISIBLE
            } else {
                loading.visibility = View.GONE

                // Handle errors here
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error

                errorState?.let {
                    Toast.makeText(this, "${it.error}", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }

}
