package com.example.cmorenomusicapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cmorenomusicapp.components.AlbumCard
import com.example.cmorenomusicapp.components.HeaderHomeScreen
import com.example.cmorenomusicapp.components.MiniPlayer
import com.example.cmorenomusicapp.components.RecentlyPlayedItem
import com.example.cmorenomusicapp.components.SubtitleComponent
import com.example.cmorenomusicapp.models.Album
import com.example.cmorenomusicapp.services.AlbumService
import com.example.cmorenomusicapp.ui.theme.LilacBackground
import com.example.cmorenomusicapp.ui.theme.OnPurpleDark
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
    var albums by remember { mutableStateOf(listOf<Album>()) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(AlbumService::class.java)
            val result = withContext(Dispatchers.IO) { service.getAllAlbums() }
            albums = result
            loading = false
        } catch (e: Exception) {
            loading = false
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
                        HeaderHomeScreen()
                        SubtitleComponent("Albums")
                        LazyRow {
                            items(albums) { album ->
                                AlbumCard(
                                    album = album,
                                    modifier = Modifier.padding(end = 16.dp),
                                    onClick = {
                                        navController.navigate(AlbumDetailScreenRoute(id = album.id))
                                    }
                                )
                            }
                        }
                        SubtitleComponent("Recently Played")
                        albums.forEach { album ->
                            RecentlyPlayedItem(
                                album = album,
                                onClick = {
                                    navController.navigate(AlbumDetailScreenRoute(id = album.id))
                                }
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }
                // MiniPlayer flotante dentro del Box y alineado abajo
                MiniPlayer(
                    album = albums.firstOrNull(),
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
fun HomeScreenPreview() {
    HomeScreen(
        innerPadding = PaddingValues(),
        navController = rememberNavController()
    )
}