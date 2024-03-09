package com.example.aviatickets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aviatickets.model.entity.Offer
import com.example.aviatickets.model.network.ApiClient.apiService
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val _offerList = MutableLiveData<List<Offer>?>()
    val offerList: MutableLiveData<List<Offer>?>
        get() = _offerList

    init {
        initList()
    }

    fun initList() {
        viewModelScope.launch {
            _offerList.value= apiService.offerList()
        }
    }

    fun sortByPrice() {
        val sortedList = _offerList.value?.sortedBy { it.price }
        _offerList.value=sortedList
    }

    fun sortByDuration() {
        val sortedList = _offerList.value?.sortedBy { it.flight.duration }
        _offerList.postValue(sortedList)
    }
}