package com.UAS.hololiveviewer.ui.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.UAS.hololiveviewer.data.live.Upcoming
import com.UAS.hololiveviewer.network.HoloApi
import com.UAS.hololiveviewer.ui.live.HololiveApiStatus
import kotlinx.coroutines.launch
import java.nio.channels.Channel

enum class HoloLiveApiStatus { LOADING, ERROR, DONE }
class CharactersViewModel : ViewModel() {
    private val _status = MutableLiveData<HololiveApiStatus>()
    val status: LiveData<HololiveApiStatus> = _status

    private val _characters = MutableLiveData<List<Channel>?>()
    val characters: MutableLiveData<List<Channel>?> = _characters

    private val _character = MutableLiveData<Channel>()
    val character: LiveData<Channel> = _character

    init {
        getCharactersData()
    }
    fun getCharactersData() {
        viewModelScope.launch {
            _status.value = HololiveApiStatus.LOADING
            try {
                _characters.value = HoloApi.retrofitService.getDataChannel().channel
                _status.value = HololiveApiStatus.DONE
            } catch (e: Exception) {
                _status.value = HololiveApiStatus.ERROR
                _characters.value = listOf()
                e.message?.let { Log.i("Pesan Error", it) }
            }
        }
    }

    fun onCharactersItemClicked(chara: Channel) {
        _character.value = chara
    }
}