package com.test.bazar

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val preferences = getSharedPreferences("setting", Context.MODE_PRIVATE)
            val viewModel: BazarViewModel = viewModel()
            MainScreen(viewModel(), preferences)
//            AppScreen()
        }
    }
}

