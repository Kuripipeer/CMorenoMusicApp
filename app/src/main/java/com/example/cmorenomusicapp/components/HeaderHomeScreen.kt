package com.example.cmorenomusicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cmorenomusicapp.ui.theme.OnPurpleDark
import com.example.cmorenomusicapp.ui.theme.PurpleLight
import com.example.cmorenomusicapp.ui.theme.PurpleMedium

@Composable
fun HeaderHomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(PurpleLight, PurpleMedium),
                )
            )
            .padding(26.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menú",
                tint = OnPurpleDark
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar",
                tint = OnPurpleDark
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                text = "Good Morning!",
                style = MaterialTheme.typography.titleMedium,
                color = OnPurpleDark,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 6.dp)
            )
            Text(
                text = "Christian Moreno",
                style = MaterialTheme.typography.headlineSmall,
                color = OnPurpleDark,
                fontWeight = FontWeight.Bold
            )
        }
    }
}