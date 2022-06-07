package com.uas.hololiveviewer.data.video

import com.squareup.moshi.Json

data class VideosItem(

	@Json(name="id")
	val id: Int? = null,

	@Json(name="yt_video_key")
	val ytVideoKey: String? = null,

	@Json(name="channel")
	val channel: Channel? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="live_schedule")
	val liveSchedule: Any? = null,

	@Json(name="is_captioned")
	val isCaptioned: Any? = null,

	@Json(name="published_at")
	val publishedAt: Any? = null,

	@Json(name="is_uploaded")
	val isUploaded: Any? = null
)