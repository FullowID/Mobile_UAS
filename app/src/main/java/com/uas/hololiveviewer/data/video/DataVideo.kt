package com.uas.hololiveviewer.data.video

import com.squareup.moshi.Json

data class DataVideo(

	@Json(name="videos")
	val videos: List<VideosItem>? = null
)