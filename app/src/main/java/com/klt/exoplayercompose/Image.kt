package com.klt.exoplayercompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun ImageScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val imageUrl =
        "https://lumiere-a.akamaihd.net/v1/images/image_9ba695c6.jpeg?region=0%2C0%2C1400%2C2100"
    val postImage = rememberCoilPainter(
        request = imageUrl,
        fadeIn = true,
        fadeInDurationMs = 500,
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Image Screen")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "image",
                            tint = MaterialTheme.colors.surface
                        )
                    }

                }
            )
        }) {
        val scale = remember { mutableStateOf(1f) }
        val rotationState = remember { mutableStateOf(1f) }


        Box(
            modifier = modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, _, zoom, rotation ->
                        scale.value *= zoom
                        rotationState.value += rotation
                    }
                },
            contentAlignment = Alignment.Center
        ) {

            Image(
                modifier = modifier
                    .align(Alignment.Center)
                    .graphicsLayer(
                        scaleX = maxOf(0.5f, minOf(3f, scale.value)),
                        scaleY = maxOf(0.5f, minOf(3f, scale.value)),
                        rotationZ = rotationState.value
                    ),
                painter = postImage,
                contentDescription = "image"
            )
        }
    }
}