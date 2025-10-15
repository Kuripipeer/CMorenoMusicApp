package com.example.cmorenomusicapp.screens

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.cmorenomusicapp.models.Album
import com.example.cmorenomusicapp.services.AlbumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun AlbumDetailScreen(id: String?, innerPadding: PaddingValues) {
    val BASE_URL = "https://music.juanfrausto.com/api/"
    var product by remember {
        mutableStateOf<Album?>(null)
    }
    var loading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(true) {
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(AlbumService::class.java)
            val result = withContext(Dispatchers.IO){
                service.getAlbumById(id)
            }
            product = result
            Log.i("AlbumDetail", product.toString())
            loading = false
        }catch (e: Exception) {
            Log.e("AlbumDetail", e.toString())
            loading = false
        }
    }

}