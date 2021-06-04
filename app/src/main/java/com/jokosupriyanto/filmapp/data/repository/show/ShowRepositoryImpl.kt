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

        call.enqueue(object : Callback<List<Result?>> {
            override fun onResponse(call: Call<List<Result?>>, response: Response<List<Result?>>) {
                if (response.isSuccessful){
                    if (response.body()?.count()!! < 1)
                        callback.failed("not found")
                    else
                        callback.success(response.body())
                }
            }
            override fun onFailure(call: Call<List<Result?>>, t: Throwable) {
                callback.failed(t.message)
            }
        })
    }

    interface Callback1 {
        fun success(result: List<Result?>?)
        fun failed(error: String?)
    }
}