package com.example.cmorenomusicapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cmorenomusicapp.models.Album

@Composable
fun AlbumTracksList(album: Album?) {
    Column {
        repeat(10) { index ->
            RecentlyPlayedItem (
                album = album?.copy(
                    title = "${album.title} • Track ${index + 1}"
                ),
                onClick = { }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}