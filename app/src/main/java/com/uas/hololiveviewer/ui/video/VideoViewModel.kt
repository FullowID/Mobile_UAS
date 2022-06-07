package com.uas.hololiveviewer.ui.video

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uas.hololiveviewer.data.video.VideosItem
import com.uas.hololiveviewer.network.HoloApi
import kotlinx.coroutines.launch

enum class VideoApiStatus { LOADING, ERROR, DONE }
class VideoViewModel : ViewModel() {

    private val _status = MutableLiveData<VideoApiStatus>()
    val status: LiveData<VideoApiStatus> = _status

    private var _hololiveVideos = MutableLiveData<List<VideosItem>?>()
    val hololiveVideos: MutableLiveData<List<VideosItem>?> = _hololiveVideos

    private var _hololiveVideo = MutableLiveData<VideosItem>()
    val hololiveVideo: LiveData<VideosItem> = _hololiveVideo

    fun getHololiveData() {
        viewModelScope.launch {
            _status.value = VideoApiStatus.LOADING
            try {
                _hololiveVideos.value = HoloApi.retrofitService.getDataVideo().videos
                _status.value = VideoApiStatus.DONE
            } catch (e: Exception) {
                _status.value = VideoApiStatus.ERROR
                _hololiveVideos.value = listOf()
                e.message?.let { Log.i("Pesan Error", it) }
            }
        }
    }

    fun onHololiveItemClicked(holo: VideosItem) {
        _hololiveVideo.value = holo
    }
}