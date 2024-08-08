package com.itssagnikmukherjee.splashscreen.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.itssagnikmukherjee.splashscreen.R
import com.itssagnikmukherjee.splashscreen.ui.theme.mukta

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen() {
    val pagerState = rememberPagerState()
    val list = getList()
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        HorizontalPager(count = list.size, state = pagerState) {
            val composition by rememberLottieComposition(list[it].image)
            Column(
                modifier = Modifier.fillMaxSize(.8f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(text = "${list[it].title}", fontFamily = mukta, fontWeight = FontWeight.ExtraBold, fontSize = 30.sp)
                LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
                Text(text = "${list[it].description}", fontFamily = mukta)
            }
        }
        HorizontalPagerIndicator(pagerState=pagerState,pageCount = list.size, indicatorWidth = 20.dp, indicatorHeight = 15.dp)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "")
        }
    }
}

data class PagerContent(
    val image: LottieCompositionSpec,
    val title: String,
    val description: String,
)

fun getList():List<PagerContent>{
    return listOf(
        PagerContent(LottieCompositionSpec.RawRes(R.raw.onboarding2), "Explore Destinations", "Find and book amazing travel experiences with ease. From stunning beaches to bustling cities, your perfect getaway is just a tap away."),
        PagerContent(LottieCompositionSpec.RawRes(R.raw.onboarding2), "Seamless Bookings", "Book flights, hotels, and activities effortlessly. Enjoy hassle-free travel planning experience with our comprehensive booking."),
        PagerContent(LottieCompositionSpec.RawRes(R.raw.onboarding2), "Best Recommendations", "Receive personalized recommendations based on your preferences. Let us help you create unforgettable travel memories."),
    )
}
