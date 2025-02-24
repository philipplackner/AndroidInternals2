package com.plcoding.androidinternals2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.androidinternals2.service.MusicControlsUi
import com.plcoding.androidinternals2.ui.theme.AndroidInternals2Theme

class MainActivity : ComponentActivity() {

    private val musicController by lazy {
        (application as MusicApp).musicServiceController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidInternals2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MusicControlsUi(
                        controller = musicController,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}