package com.uas.hololiveviewer.data.video

import com.squareup.moshi.Json

data class Channel(

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
	val id: Int? = null,

	@Json(name="bb_space_id")
	val bbSpaceId: Any? = null,

	@Json(name="published_at")
	val publishedAt: String? = null,

	@Json(name="yt_channel_id")
	val ytChannelId: String? = null,

	@Json(name="view_count")
	val viewCount: Int? = null
)