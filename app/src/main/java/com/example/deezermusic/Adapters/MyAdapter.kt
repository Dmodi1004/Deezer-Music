package com.example.deezermusic.Adapters

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.deezermusic.Models.Data
import com.example.deezermusic.R
import com.example.deezermusic.databinding.ItemListBinding
import com.squareup.picasso.Picasso

class MyAdapter(private val context: Context, private var dataList: List<Data>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Data, context: Context) {
            binding.apply {


                musicTitle.text = model.title

                Picasso.get()
                    .load(model.album.cover)
                    .placeholder(R.drawable.ic_user_avatar)
                    .into(musicImage)

                val mediaPlayer = MediaPlayer.create(context, model.preview.toUri())

                btnPlay.setOnClickListener {
                    mediaPlayer.start()
                }
                btnPause.setOnClickListener {
                    mediaPlayer.pause()
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(model = dataList[position], context = context)
    }
}