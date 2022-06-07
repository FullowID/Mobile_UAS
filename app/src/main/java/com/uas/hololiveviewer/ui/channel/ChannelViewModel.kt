package com.uas.hololiveviewer.ui.channel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uas.hololiveviewer.data.channel.ChannelsItem
import com.uas.hololiveviewer.network.HoloApi
import kotlinx.coroutines.launch

enum class ChannelApiStatus { LOADING, ERROR, DONE }
class ChannelViewModel : ViewModel() {

    private val _status = MutableLiveData<ChannelApiStatus>()
    val status: LiveData<ChannelApiStatus> = _status

    private var _hololiveChannels= MutableLiveData<List<ChannelsItem?>?>()
    val hololiveChannels: MutableLiveData<List<ChannelsItem?>?> = _hololiveChannels

    private var _hololiveChannel = MutableLiveData<ChannelsItem>()
    val hololiveChannel: LiveData<ChannelsItem> = _hololiveChannel

    fun getHololiveData() {
        viewModelScope.launch {
            _status.value = ChannelApiStatus.LOADING
            try {
                _hololiveChannels.value = HoloApi.retrofitService.getDataChannel().channels
                _status.value = ChannelApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ChannelApiStatus.ERROR
                _hololiveChannels.value = listOf()
                e.message?.let { Log.i("Pesan Error", it) }
            }
        }
    }

    fun onHololiveItemClicked(holo: ChannelsItem) {
        _hololiveChannel.value = holo
    }
}