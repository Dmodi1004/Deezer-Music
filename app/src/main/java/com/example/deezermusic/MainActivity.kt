package com.example.deezermusic

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        loading = findViewById(R.id.loading)

        mediaPlayer = MediaPlayer()

        adapter = MyAdapter(mediaPlayer)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        viewModel.getData("eminem").observe(this) {

            if (it != null) {
                adapter.submitData(lifecycle, it)
            }
            loading.visibility = View.GONE
        }

    }

    override fun onDestroy() {
        mediaPlayer.release()
        super.onDestroy()
    }

}
