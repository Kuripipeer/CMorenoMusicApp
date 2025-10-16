package com.example.cmorenomusicapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cmorenomusicapp.components.HeaderHomeScreen
import com.example.cmorenomusicapp.components.MiniPlayer
import com.example.cmorenomusicapp.models.Album
import com.example.cmorenomusicapp.services.AlbumService
import com.example.cmorenomusicapp.ui.theme.LilacBackground
import com.example.cmorenomusicapp.ui.theme.OnPurpleDark
import com.example.cmorenomusicapp.ui.theme.PurpleDark
import com.example.cmorenomusicapp.ui.theme.PurpleLight
import com.example.cmorenomusicapp.ui.theme.PurpleMedium
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
        mutableStateOf(false)
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

    Scaffold(
        bottomBar = { MiniPlayer(album = albums.firstOrNull()) }
    ) { contentPadding ->
        if (loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(LilacBackground, OnPurpleDark)
                        )
                    )
                    .padding(contentPadding)
                    .padding(15.dp)
            ) {
                item {
                    // Header
                    HeaderHomeScreen()


                    LazyRow {
                        items(albums) { album ->
                            // Aquí tu item de álbum
                        }
                    }
                }
                // Otros items de la columna
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    // Puedes pasar valores dummy para innerPadding y navController
    HomeScreen(
        innerPadding = PaddingValues(),
        navController = rememberNavController()
    )
}