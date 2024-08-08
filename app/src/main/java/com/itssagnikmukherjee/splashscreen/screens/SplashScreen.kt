package com.itssagnikmukherjee.splashscreen.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.itssagnikmukherjee.splashscreen.MainActivity
import com.itssagnikmukherjee.splashscreen.R
import kotlinx.coroutines.delay

@Preview
@Composable
fun SplashScreen(nav: NavController, context: MainActivity) {

    //this will navigate to onboarding screen or home screen after 1 sec

    LaunchedEffect(key1 = true) {
        delay(1000)

        if (onBoardingIsFinished(context)){
            nav.popBackStack()
            nav.navigate("home")
        }else{
            nav.popBackStack()
            nav.navigate("onboarding")
        }

    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bootanimation))
    val loaderComp by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loaderanimation))
    var isPlaying by remember { mutableStateOf(true) }
    val progress by animateLottieCompositionAsState(composition = composition, isPlaying=isPlaying)

    LaunchedEffect(key1 = progress) {
        if (progress==0f){
            isPlaying = true
        }
        if (progress==1f){
            isPlaying = false
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .fillMaxHeight()
                .zIndex(1f),
            )
    }
}

private fun onBoardingIsFinished(context:MainActivity):Boolean{
    val sharedPref = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
    return sharedPref.getBoolean("isFinished",false)
}