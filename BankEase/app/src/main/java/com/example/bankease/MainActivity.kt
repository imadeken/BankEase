package com.example.bankease

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import com.example.bankease.ui.theme.BankEaseTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bankease.ui.theme.Profile

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankEaseTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "Slide1"
                    ) {
                        composable("Slide1"){
                            WelcomeScreen(navController = navController, context = this@MainActivity)
                        }
                        composable("Get Started") {
                            GetStarted(navController = navController,  context = this@MainActivity)
                        }
                        composable("Home") {
                            HomeScreen(navController = navController)
                        }
                        composable("Profile") {
                            Profile()
                        }
                    }
                }
            }
        }
    }
}