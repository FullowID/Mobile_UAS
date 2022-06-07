package com.uas.hololiveviewer.ui.channel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uas.hololiveviewer.data.channel.ChannelsItem
import com.uas.hololiveviewer.databinding.ChannelItemBinding

class ChannelAdapter(private val clickListener: ChListener) :
    ListAdapter<ChannelsItem, ChannelAdapter.ChannelViewHolder>(DiffCallback) {
    class ChannelViewHolder(
        var binding: ChannelItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: ChListener, hololive: ChannelsItem) {
            binding.holoItem = hololive
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ChannelsItem>() {
        override fun areItemsTheSame(oldItem: ChannelsItem, newItem: ChannelsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChannelsItem, newItem: ChannelsItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChannelViewHolder(
            ChannelItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        val holo = getItem(position)
        holder.bind(clickListener, holo)
    }

}

class ChListener(val clickListener: (holo: ChannelsItem) -> Unit) {
    fun onClick(holo: ChannelsItem) = clickListener(holo)
}
