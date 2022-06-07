package com.uas.hololiveviewer.ui.live

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uas.hololiveviewer.data.live.Upcoming
import com.uas.hololiveviewer.network.HoloApi
import kotlinx.coroutines.launch

enum class UpcomingApiStatus { LOADING, ERROR, DONE }
class UpcomingStreamViewModel : ViewModel() {

    private val _status = MutableLiveData<UpcomingApiStatus>()
    val status: LiveData<UpcomingApiStatus> = _status

    private var _hololiveUpcomings = MutableLiveData<List<Upcoming>?>()
    val hololiveUpcomings: MutableLiveData<List<Upcoming>?> = _hololiveUpcomings

    private var _hololiveUpcoming = MutableLiveData<Upcoming>()
    val hololiveUpcoming: LiveData<Upcoming> = _hololiveUpcoming

    fun getHololiveData() {
        viewModelScope.launch {
            _status.value = UpcomingApiStatus.LOADING
            try {
                _hololiveUpcomings.value = HoloApi.retrofitService.getDataUpcoming().upcoming
                _status.value = UpcomingApiStatus.DONE
            } catch (e: Exception) {
                _status.value = UpcomingApiStatus.ERROR
                _hololiveUpcomings.value = listOf()
                e.message?.let { Log.i("Pesan Error", it) }
            }
        }
    }

    fun onHololiveItemClicked(holo: Upcoming) {
        _hololiveUpcoming.value = holo
    }
}