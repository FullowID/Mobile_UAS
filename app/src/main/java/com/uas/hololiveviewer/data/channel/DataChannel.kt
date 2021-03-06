package com.uas.hololiveviewer.data.channel

import com.squareup.moshi.Json

data class DataChannel(

	@Json(name="channels")
	val channels: List<ChannelsItem?>? = null
)

data class ChannelsItem(

	@Json(name="twitter_link")
	val twitterLink: String? = null,

	@Json(name="subscriber_count")
	val subscriberCount: Int? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="video_count")
	val videoCount: Int? = null,

	@Json(name="description")
	val description: String? = null,

	@Json(name="photo")
	val photo: String? = null,

	@Json(name="id")
	val id: Int? = null
)
