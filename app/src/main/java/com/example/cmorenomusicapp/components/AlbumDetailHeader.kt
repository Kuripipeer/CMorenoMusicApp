package com.example.cmorenomusicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.cmorenomusicapp.models.Album
import com.example.cmorenomusicapp.ui.theme.LilacBackground
import com.example.cmorenomusicapp.ui.theme.OnPurpleDark
import com.example.cmorenomusicapp.ui.theme.PurpleDark
import com.example.cmorenomusicapp.ui.theme.PurpleLight
import com.example.cmorenomusicapp.ui.theme.PurpleMedium

@Composable
fun AlbumDetailHeader(
    album: Album?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(LilacBackground)
    ) {

        AsyncImage(
            model = album?.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(32.dp))
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(32.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            PurpleDark.copy(alpha = 0.7f)
                        ),
                        startY = 0f,
                        endY = 610f
                    )
                )
        )

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Volver",
            tint = OnPurpleDark,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
                .size(42.dp)
                .background(Color.Black.copy(alpha = 0.7f), shape = CircleShape)
                .padding(5.dp)
        )

        Icon(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = "Favorito",
            tint = OnPurpleDark,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopEnd)
                .size(42.dp)
                .background(Color.Black.copy(alpha = 0.7f), shape = CircleShape)
                .padding(5.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 24.dp, bottom = 100.dp)
        ) {
            album?.title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            album?.artist?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 24.dp, bottom = 24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play",
                tint = OnPurpleDark,
                modifier = Modifier
                    .size(64.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(PurpleLight, PurpleMedium)
                        ),
                        shape = CircleShape
                    )
                    .padding(15.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = PurpleDark,
                modifier = Modifier
                    .size(64.dp)
                    .background(OnPurpleDark, shape = CircleShape)
                    .padding(15.dp)
            )
        }
    }
}
