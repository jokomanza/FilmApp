package com.jokosupriyanto.filmapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jokosupriyanto.filmapp.data.model.Result
import com.jokosupriyanto.filmapp.data.model.Show
import com.jokosupriyanto.filmapp.data.repository.show.ShowRepository
import com.jokosupriyanto.filmapp.data.repository.show.ShowRepositoryImpl

class ViewModel: ViewModel() {
    private val result = MutableLiveData<Show?>()
    private val repository: ShowRepository = ShowRepositoryImpl()
    public var error: String? = ""

    fun fetchDetails(key: String) {
        repository.findShow(key, object:ShowRepositoryImpl.Callback1 {
            override fun success(show: Show?) {
                result.postValue(show)
            }

            override fun failed(error: String?) {
                this@ViewModel.error = error
                result.postValue(null)
            }
        })

    }

    fun getDetail(): LiveData<Show?> {
        return result
    }


}