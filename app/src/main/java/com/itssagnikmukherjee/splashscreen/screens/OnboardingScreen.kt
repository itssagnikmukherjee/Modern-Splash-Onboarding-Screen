package com.itssagnikmukherjee.splashscreen.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Preview
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen() {
    val pagerState = rememberPagerState()
    val list = getList()
    val scope = rememberCoroutineScope()
    Row (
        modifier = Modifier.fillMaxWidth().padding(20.dp).offset(x=-10.dp,y=40.dp).zIndex(2f),
        horizontalArrangement = Arrangement.End,
    ){
        Button(onClick = {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage+2)
            }
        }, colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ), modifier = Modifier.zIndex(2f)){
        Text(text = "SKIP", fontFamily = mukta, fontSize = 16.sp, fontWeight = FontWeight.Light, color = Color.Gray)
        }
    }
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        HorizontalPager(count = list.size, state = pagerState, modifier = Modifier
            .weight(1f)
            .padding(10.dp)) {
            val composition by rememberLottieComposition(list[it].image)
            Column(
                modifier = Modifier.fillMaxSize(.8f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(text = "${list[it].title}", fontFamily = mukta, fontWeight = FontWeight.ExtraBold, fontSize = 38.sp, modifier = Modifier.fillMaxHeight(0.2f), lineHeight = 40.sp)
                LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever, modifier = Modifier.fillMaxHeight(0.5f))
                Text(text = "${list[it].description}", fontFamily = mukta, modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .padding(top = 50.dp), style = TextStyle(
                    textAlign = TextAlign.Justify,
                    fontSize = 16.sp
                ))

            }

        }
        Button(onClick = {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage+1)
            }
        }, modifier = Modifier.offset(y = -130.dp), colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        )) {
            if (pagerState.currentPage==list.size-1){
                Icon(imageVector = Icons.Filled.Check, contentDescription = "")
            }
            else{Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "")}
        }
        HorizontalPagerIndicator(pagerState=pagerState,pageCount = list.size, indicatorWidth = 20.dp, indicatorHeight = 15.dp, modifier = Modifier.offset(0.dp,-70.dp))
    }
}

data class PagerContent(
    val image: LottieCompositionSpec,
    val title: String,
    val description: String,
    val bgColor: Color
)

fun getList():List<PagerContent>{
    return listOf(
        PagerContent(
            LottieCompositionSpec.RawRes(R.raw.onboarding1),
            "Explore all Destinations",
            "Find and book amazing travel experiences with ease. From stunning beaches to bustling cities, your perfect getaway is just a tap away.",
            Color.White
        ),
        PagerContent(LottieCompositionSpec.RawRes(R.raw.onboarding2), "Seamless fast Bookings", "Book flights, hotels, and activities effortlessly. Enjoy hassle-free travel planning experience with our comprehensive booking.",
            Color.White
        ),
        PagerContent(LottieCompositionSpec.RawRes(R.raw.onboarding3), "The Best Preferences", "Receive personalized recommendations based on your preferences. Let us help you create unforgettable travel memories.",
            Color.White
        ),
    )
}
