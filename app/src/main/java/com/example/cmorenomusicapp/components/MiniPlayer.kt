package com.example.cmorenomusicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.cmorenomusicapp.models.Album
import com.example.cmorenomusicapp.ui.theme.OnPurpleDark
import com.example.cmorenomusicapp.ui.theme.OnPurpleDarkSecondary
import com.example.cmorenomusicapp.ui.theme.PurpleDark

@Composable
fun MiniPlayer(album: Album?) {
    Surface(
        color = PurpleDark,
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 8.dp,
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ){
            AsyncImage(
                model = album?.image,
                contentDescription = album?.title,
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            ) {
                album?.title?.let {
                    Text(
                        text = it,
                        color = OnPurpleDark,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                album?.artist?.let {
                    Text(
                        text = it,
                        color = OnPurpleDarkSecondary,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                tint = Color(0xFF3F206E),
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(8.dp)

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MiniPlayerPreview() {
    val albumDemo = Album(
        title = "Tales of Ithiria",
        artist = "Haggard",
        image = "https://upload.wikimedia.org/wikipedia/en/7/7e/Haggard_-_Tales_of_Ithiria.jpg",
        id = "ioaioasfiasf",
        description = "Prueba"
    )
    MiniPlayer(album = albumDemo)
}