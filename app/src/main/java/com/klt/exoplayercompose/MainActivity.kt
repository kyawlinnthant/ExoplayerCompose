package com.klt.exoplayercompose

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaMetadata
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.klt.exoplayercompose.theme.ExoplayerComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExoplayerComposeTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "Main"
                    ) {
                        composable(route = "Main") {
                            MainView(navController = navController)
                        }
                        composable(route = "Image") {
                            ImageScreen(navController = navController)
                        }
                        composable(route = "Video") {
                            VideoScreen(navController = navController)
                        }
                        composable(route = "Audio") {
                            AudioScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VideoPlayerView(
    modifier: Modifier = Modifier,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val videoUrl1 =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
    val mediaItem = MediaItem.fromUri(videoUrl1)
    var playWhenReady by remember { mutableStateOf(true) }
    var currentWindow by remember { mutableStateOf(0) }
    var playbackPosition by remember { mutableStateOf(0L) }
    val player = remember(context) { ExoPlayer.Builder(context).build() }

    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_START) {
                    player.apply {
                        this.setMediaItem(mediaItem)
                        this.playWhenReady = playWhenReady
                        this.seekTo(currentWindow, playbackPosition)
                        this.prepare()
                    }
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                player.run {
                    playbackPosition = this.currentPosition
                    currentWindow = this.currentWindowIndex
                    playWhenReady = this.playWhenReady
                    release()
                }
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    )


    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        AndroidView(
            modifier = modifier
                .fillMaxWidth(),
            factory = {
                PlayerView(it).apply {
                    this.player = player
                }
            })
    }

}

@Composable
fun SimplePlayerView(modifier: Modifier = Modifier) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val videoUrl1 =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    val videoUrl2 =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
    val videoUrl3 =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"
    val videoUrl4 =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"
    val videoUrl5 =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"
    val videoUrl6 =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4"
    val videoUrl7 =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"
    val videoUrl8 = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"
    val videoUrl9 =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4"
    val videoUrl10 =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"
    val videoUrl11 =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4"
    val videoUrl12 =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4"
    val videoUrl13 =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4"

    val urlLink = listOf<String>(
        videoUrl1,
        videoUrl2,
        videoUrl3,
        videoUrl4,
        videoUrl5,
        videoUrl6,
        videoUrl7,
        videoUrl8,
        videoUrl9,
        videoUrl10,
        videoUrl11,
        videoUrl12,
        videoUrl13
    )
    val mediaItems = arrayListOf<MediaItem>()
    urlLink.forEachIndexed { index, data ->
        mediaItems.add(
            MediaItem.Builder().setUri(data)
                .setMediaId(index.toString())
                .setMediaMetadata(
                    MediaMetadata.Builder().setDisplayTitle(index.toString()).build()
                )
                .build()
        )
    }
    val mediaItem = MediaItem.fromUri(videoUrl1)
    var playWhenReady by remember { mutableStateOf(true) }
    var currentWindow by remember { mutableStateOf(0) }
    var playbackPosition by remember { mutableStateOf(0L) }
    val player = remember(context) { ExoPlayer.Builder(context).build() }

    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context).build().apply {
            this.setMediaItems(mediaItems)
            this.prepare()
            this.playWhenReady = true
        }
    }
    DisposableEffect(
        AndroidView(
            modifier = modifier.fillMaxWidth(),
            factory = {

                // exo player view for our video player
                PlayerView(context).apply {
                    this.player = exoPlayer
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
            exoPlayer.release()
        }
    }
}


