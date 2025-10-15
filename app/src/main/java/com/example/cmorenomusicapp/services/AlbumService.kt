package com.example.cmorenomusicapp.services

import com.example.cmorenomusicapp.models.Album
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {

    @GET("albums")
    suspend fun getAllAlbums() : List<Album>

    @GET("albums/{id}")
    suspend fun getAlbumById(@Path("id") id: String?) : Album

}