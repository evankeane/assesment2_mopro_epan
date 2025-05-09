package com.evankeane.assessment2_mopro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.evankeane.assessment2_mopro.ui.theme.Assessment2_MoproTheme
import com.evankeane.assessment2_mopro.ui.theme.screen.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assessment2_MoproTheme {
               MainScreen()
            }
        }
    }
}



