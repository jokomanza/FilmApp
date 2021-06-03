package com.jokosupriyanto.filmapp.data.repository.show

interface ShowRepository {

    fun findShow(key:String, callback: ShowRepositoryImpl.Callback1)
}