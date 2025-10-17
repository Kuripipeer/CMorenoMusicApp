package com.example.cmorenomusicapp.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cmorenomusicapp.ui.theme.PurpleDark
import com.example.cmorenomusicapp.ui.theme.TextSecondary

@Composable
fun ArtistInfo(artist: String?) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(24.dp),
        shadowElevation = 8.dp,
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Artist:",
                fontWeight = FontWeight.Bold,
                color = PurpleDark
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = artist ?: "Unknown Artist",
                color = TextSecondary
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}