package com.example.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PagerIndicator(size: Int, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(16.dp)
    ) {
        repeat(size) { index ->
            Box(
                modifier = Modifier
                    .size(if (index == currentPage) 12.dp else 8.dp)
                    .background(
                        color = if (index == currentPage) Color.White else Color.Gray,
                        shape = CircleShape
                    )
            )
        }
    }
}
