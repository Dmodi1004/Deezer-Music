package com.example.deezermusic.Adapters

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.deezermusic.Models.Data
import com.example.deezermusic.R
import com.example.deezermusic.databinding.ItemListBinding
import com.squareup.picasso.Picasso

/*
import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.deezermusic.Models.Data
import com.example.deezermusic.R
import com.example.deezermusic.databinding.ItemListBinding
import com.squareup.picasso.Picasso

class MyAdapter(private val context: Context, private var dataList: List<Data> , private val mediaPlayer: MediaPlayer) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemListBinding, private val mediaPlayer: MediaPlayer) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Data, context: Context) {
            binding.apply {


                musicTitle.text = model.title

                Picasso.get()
                    .load(model.album.cover)
                    .placeholder(R.drawable.ic_user_avatar)
                    .into(musicImage)

                btnPlay.setOnClickListener {

                    btnPlay.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE

                    mediaPlayer.reset()
                    mediaPlayer.setDataSource(context, model.preview.toUri())
                    mediaPlayer.prepareAsync()
                    mediaPlayer.setOnPreparedListener {
                        mediaPlayer.start()
                        btnPlay.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                }

                btnPause.setOnClickListener {
                    mediaPlayer.pause()
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListBinding.inflate(LayoutInflater.from(context), parent, false), mediaPlayer = mediaPlayer
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(model = dataList[position], context = context)
    }
}*/

class MyAdapter() : PagingDataAdapter<Data, MyAdapter.ViewHolder>(COMPARATOR) {

    class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Data?, context: Context) {
            binding.apply {
                data?.let { data ->
                    musicTitle.text = data.title

                    Picasso.get()
                        .load(data.album.cover)
                        .placeholder(R.drawable.ic_user_avatar)
                        .into(binding.musicImage)

                    val mediaPlayer = MediaPlayer()

                    btnPlay.setOnClickListener {

                        btnPlay.visibility = View.GONE
                       progressBar.visibility = View.VISIBLE

                        mediaPlayer.reset()
                        mediaPlayer.setDataSource(context, data.preview.toUri())
                        mediaPlayer.prepareAsync()
                        mediaPlayer.setOnPreparedListener {
                            mediaPlayer.start()
                         btnPlay.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                        }
                    }

                    btnPause.setOnClickListener {
                        mediaPlayer.pause()
                    }

                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), context = holder.itemView.context)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }

        }
    }

}
