package com.UAS.hololiveviewer.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.UAS.hololiveviewer.data.channel.Channel

class CharactersAdapter(private val clickListener: CharactersListener) :
    ListAdapter<Channel, CharactersAdapter.CharactersViewHolder>(DiffCallback) {
    class CharactersViewHolder(
        var binding: CharactersBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: CharactersListener, channel: Channel) {
            binding.item = channel
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<Channel>() {
        override fun areItemsTheSame(oldItem: Channel, newItem: Channel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Channel, newItem: Channel): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val layoutInflater = layoutInflater.from(parent.context)
        return CharactersViewHolder(
            CharacterBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: int) {
        val chara = getItem(position)
        holder.bind(clickListener, chara)
    }

}

class CharactersListener(val clickListener: channel: Channel) -> Unit) {
    fun onClick(channel: Channel) = clickListener(channel)
}