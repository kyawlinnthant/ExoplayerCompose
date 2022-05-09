package com.klt.exoplayercompose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainView(navController: NavController) {

    val imageUrl = remember { mutableStateOf("") }
    val audioUrl = remember { mutableStateOf("") }
    val videoUrl = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = imageUrl.value,
                onValueChange = {
                    imageUrl.value = it
                },
                placeholder = {
                    Text(text = "Image url")
                }
            )
            Button(
                onClick = {
                    navController.navigate("Image")
                }) {
                Text(text = "Image")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = audioUrl.value,
                onValueChange = {
                    audioUrl.value = it
                },
                placeholder = {
                    Text(text = "Audio url")
                }
            )

            Button(
                onClick = {
                    navController.navigate("Audio")
                }) {
                Text(text = "Audio")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = videoUrl.value,
                onValueChange = {
                    videoUrl.value = it
                },
                placeholder = {
                    Text(text = "Video url")
                }

            )
            Button(
                onClick = {
                    navController.navigate("Video")
                }) {
                Text(text = "Video")
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    Surface {
        MainView(navController = rememberNavController())
    }
}