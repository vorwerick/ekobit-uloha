package com.example.ekobituloha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ekobituloha.presentation.screens.MainScreen
import com.example.ekobituloha.presentation.theme.EkobitUlohaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EkobitUlohaTheme {
                MainScreen()
            }
        }
    }
}
