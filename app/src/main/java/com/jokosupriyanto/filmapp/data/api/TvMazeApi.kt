package com.jokosupriyanto.filmapp.data.api

import com.jokosupriyanto.filmapp.data.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TvMazeApi {

    @GET("search/shows")
    fun findBy(@Query("q") key: String): Call<Result>
}