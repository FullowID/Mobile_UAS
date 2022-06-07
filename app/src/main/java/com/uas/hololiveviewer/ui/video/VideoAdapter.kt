package com.uas.hololiveviewer.ui.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uas.hololiveviewer.data.video.VideosItem
import com.uas.hololiveviewer.databinding.VideoItemBinding

class VideoAdapter(private val clickListener: VideoListener):
    ListAdapter<VideosItem, VideoAdapter.VideoViewHolder>(DiffCallback) {
    class VideoViewHolder(
        var binding: VideoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: VideoListener, hololive: VideosItem) {
            binding.holoItem = hololive
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<VideosItem>() {
        override fun areItemsTheSame(oldItem: VideosItem, newItem: VideosItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VideosItem, newItem: VideosItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VideoViewHolder(
            VideoItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val holo = getItem(position)
        holder.bind(clickListener, holo)
    }

}

class VideoListener(val clickListener: (holo: VideosItem) -> Unit) {
    fun onClick(holo: VideosItem) = clickListener(holo)
}