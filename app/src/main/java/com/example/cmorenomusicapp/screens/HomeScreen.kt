package com.example.cmorenomusicapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.navigation.NavController
import com.example.cmorenomusicapp.models.Album
import com.example.cmorenomusicapp.services.AlbumService
import com.example.cmorenomusicapp.ui.theme.LilacBackground
import com.example.cmorenomusicapp.ui.theme.OnPurpleDark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

@Composable
fun HomeScreen(
    innerPadding: PaddingValues,
    navController: NavController
) {
    val BASE_URL = "https://music.juanfrausto.com/api/"
    var albums by remember {
        mutableStateOf(listOf<Album>())
    }
    var loading by remember {
        mutableStateOf(true)
    }

    // EFECTOS SECUNDARIOS
    LaunchedEffect(true) {
        try {
            Log.i("HomeScreen", "Creando instancia de Retrofit")
            // INSTANCIA DE RETROFIT
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(AlbumService::class.java)
            val result = withContext(Dispatchers.IO){
                service.getAllAlbums()
            }
            Log.i("HomeScreen", "Resultado: $result")
            albums = result
            loading = false
        } catch (e : Exception){
            Log.e("HomeScreen", e.toString())
            loading = false
        }
    }

    if (loading){
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    } else{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(LilacBackground, OnPurpleDark)
                    )
                )
        ) {

        }
    }

}