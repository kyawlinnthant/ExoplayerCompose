package com.klt.exoplayercompose

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun VideoScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val context = LocalContext.current
    val videoUrl1 =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
    val mediaItem = MediaItem.fromUri(videoUrl1)
    val player = remember(context) {

        ExoPlayer.Builder(context).build().apply {
            this.setMediaItem(mediaItem)
            this.prepare()
            this.playWhenReady = true
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Video Screen")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "video",
                            tint = MaterialTheme.colors.surface
                        )
                    }

                }
            )
        }) {
        DisposableEffect(
            AndroidView(
                modifier = modifier.fillMaxWidth(),
                factory = {
                    PlayerView(it).apply {
                        this.player = player
                        layoutParams =
                            FrameLayout.LayoutParams(
                                ViewGroup.LayoutParams
                                    .MATCH_PARENT,
                                ViewGroup.LayoutParams
                                    .MATCH_PARENT
                            )
                    }
                }
            )
        ) {
            onDispose {
                // release player when no longer needed
                player.release()
            }
        }
    }


}