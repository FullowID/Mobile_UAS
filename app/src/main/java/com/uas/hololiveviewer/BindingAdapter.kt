package com.uas.hololiveviewer

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.uas.hololiveviewer.data.channel.ChannelsItem
import com.uas.hololiveviewer.data.live.Upcoming
import com.uas.hololiveviewer.data.video.VideosItem
import com.uas.hololiveviewer.ui.channel.ChannelAdapter
import com.uas.hololiveviewer.ui.channel.ChannelApiStatus
import com.uas.hololiveviewer.ui.live.UpcomingApiStatus
import com.uas.hololiveviewer.ui.live.UpcomingStreamAdapter
import com.uas.hololiveviewer.ui.video.VideoAdapter
import com.uas.hololiveviewer.ui.video.VideoApiStatus

@BindingAdapter("listUpcoming")
fun binRVUpcoming(recyclerView: RecyclerView, data: List<Upcoming>?) {
    val adapter = recyclerView.adapter as UpcomingStreamAdapter
    adapter.submitList(data)
}

@BindingAdapter("listVideo")
fun bindRVVideo(recyclerView: RecyclerView, data: List<VideosItem>?) {
    val adapter = recyclerView.adapter as VideoAdapter
    adapter.submitList(data)
}

@BindingAdapter("listChannel")
fun bindRVChannel(recyclerView: RecyclerView, data: List<ChannelsItem>?) {
    val adapter = recyclerView.adapter as ChannelAdapter
    adapter.submitList(data)
}



@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("channelApiStatus")
fun bindStatus(statusImageView: ImageView, status: ChannelApiStatus?) {
    when (status) {
        ChannelApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ChannelApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ChannelApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("upcomingApiStatus")
fun bindUpcomingStatus(statusImageView: ImageView, status: UpcomingApiStatus?) {
    when (status) {
        UpcomingApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        UpcomingApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        UpcomingApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("videoApiStatus")
fun bindVideoStatus(statusImageView: ImageView, status: VideoApiStatus?) {
    when (status) {
        VideoApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        VideoApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        VideoApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}