package com.example.cmorenomusicapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.cmorenomusicapp.components.AboutThisAlbumSection
import com.example.cmorenomusicapp.components.AlbumDetailHeader
import com.example.cmorenomusicapp.components.AlbumTracksList
import com.example.cmorenomusicapp.components.ArtistInfo
import com.example.cmorenomusicapp.components.MiniPlayer
import com.example.cmorenomusicapp.components.RecentlyPlayedItem
import com.example.cmorenomusicapp.models.Album
import com.example.cmorenomusicapp.services.AlbumService
import com.example.cmorenomusicapp.ui.theme.LilacBackground
import com.example.cmorenomusicapp.ui.theme.OnPurpleDark
import com.example.cmorenomusicapp.ui.theme.PurpleDark
import com.example.cmorenomusicapp.ui.theme.TextSecondary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun AlbumDetailScreen(id: String?) {
    val BASE_URL = "https://music.juanfrausto.com/api/"
    var album by remember { mutableStateOf<Album?>(null) }
    var loading by remember { mutableStateOf(true) }
    var errorMsg by remember { mutableStateOf<String?>(null) }

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
            album = result
            Log.i("AlbumDetail", album.toString())
            loading = false
        }catch (e: Exception) {
            Log.e("AlbumDetail", e.toString())
            loading = false
            errorMsg = when (e) {
                is java.net.UnknownHostException -> "Sin conexión a Internet. Por favor revisa tu conexión."
                else -> "Error al cargar el álbum: ${e.localizedMessage ?: "intenta de nuevo"}"
            }
        }
    }

    Scaffold { contentPadding ->
        if (!loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(LilacBackground, OnPurpleDark)
                        )
                    )
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadding)
                        .padding(top = 5.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                ) {
                    item {
                        // Header
                        AlbumDetailHeader(album = album)

                        //Espaciado
                        Spacer(modifier = Modifier.height(20.dp))

                        // Sobre este album
                        AboutThisAlbumSection(
                            album?.description
                        )

                        ArtistInfo(album?.artist)

                        Spacer(modifier = Modifier.height(20.dp))

                        // Lista de canciones
                        AlbumTracksList(album = album)
                    }
                }

                MiniPlayer(
                    album = album,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                        .zIndex(1f)
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumDetailScreenPreview() {
    AlbumDetailScreen(
        id = "1",
    )
}