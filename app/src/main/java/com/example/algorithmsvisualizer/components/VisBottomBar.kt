package com.example.algorithmsvisualizer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.algorithmsvisualizer.R

@Composable
fun VisBottomBar(
    modifier: Modifier = Modifier,
    playPauseClick: () -> Unit,
    slowDownClick: () -> Unit,
    speedUpClick: () -> Unit,
    previousClick: () -> Unit,
    nextClick: () -> Unit,
    isPlaying: Boolean = false
) {
    BottomAppBar(
        modifier = modifier
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            IconButton(onClick = slowDownClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_horizontal),
                    contentDescription = "Slow down",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(onClick = playPauseClick) {
                Icon(
                    painter = painterResource(id = if (!isPlaying) {
                        R.drawable.ic_play
                    } else {
                        R.drawable.ic_pause
                    }),
                    contentDescription = "Play Pause",
                    tint = Color.White
                )
            }

            IconButton(onClick = speedUpClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Speed up",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(onClick = previousClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Previous step",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(onClick = nextClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_forward),
                    contentDescription = "Next step",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

    }
}