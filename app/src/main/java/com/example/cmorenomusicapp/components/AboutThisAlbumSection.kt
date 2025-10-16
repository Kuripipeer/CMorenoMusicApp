package com.example.cmorenomusicapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cmorenomusicapp.ui.theme.PurpleDark
import com.example.cmorenomusicapp.ui.theme.TextSecondary

@Composable
fun AboutThisAlbumSection(description: String?) {
    Surface (
        shape = RoundedCornerShape(24.dp),
        shadowElevation = 10.dp,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = "About this album",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = description ?: "No description available.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextSecondary
                )
            )
        }
    }
}