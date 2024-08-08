package com.itssagnikmukherjee.splashscreen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itssagnikmukherjee.splashscreen.screens.HomeScreen
import com.itssagnikmukherjee.splashscreen.screens.OnboardingScreen
import com.itssagnikmukherjee.splashscreen.screens.SplashScreen
import com.itssagnikmukherjee.splashscreen.ui.theme.SplashScreenTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplashScreenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    //    for the navigation
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "splash"){
                        composable("splash"){ SplashScreen(navController,context = this@MainActivity)}
                        composable("onboarding"){ OnboardingScreen(navController,context=this@MainActivity) }
                        composable("home"){ HomeScreen() }
                    }
                }
            }
        }
    }
}
