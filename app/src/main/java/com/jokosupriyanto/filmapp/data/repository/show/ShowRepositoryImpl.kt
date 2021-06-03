package com.jokosupriyanto.filmapp.data.repository.show

import android.util.Log
import com.jokosupriyanto.filmapp.data.api.TvMazeApi
import com.jokosupriyanto.filmapp.data.model.Result
import com.jokosupriyanto.filmapp.data.model.Show
import com.jokosupriyanto.filmapp.data.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowRepositoryImpl : ShowRepository {
    override fun findShow(key: String, callback: Callback1) {
        val request = ApiService.buildService(TvMazeApi::class.java)
        val call = request.findBy(key)

        call.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful){
                    callback.success(response.body()?.show)
                }
            }
            override fun onFailure(call: Call<Result>, t: Throwable) {
                callback.failed(t.message)
            }
        })
    }

    interface Callback1 {
        fun success(result: Show?)
        fun failed(error: String?)
    }
}