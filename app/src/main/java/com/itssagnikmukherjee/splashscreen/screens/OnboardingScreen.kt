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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.itssagnikmukherjee.splashscreen.R

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
            Column(
                modifier = Modifier.fillMaxSize(.8f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(text = "${list[it].title}")
                Image(painter = painterResource(id = list[it].image), contentDescription = "")
                Text(text = "${list[it].description}")
            }
        }
        HorizontalPagerIndicator(pagerState=pagerState,pageCount = list.size)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "")
        }
    }
}

data class PagerContent(
    val image: Int,
    val title: String,
    val description: String,
)

fun getList():List<PagerContent>{
    return listOf(
        PagerContent(R.drawable.ic_launcher_background, "title1", "description1"),
        PagerContent(R.drawable.ic_launcher_background, "title2", "description2"),
        PagerContent(R.drawable.ic_launcher_background, "title3", "description3"),
    )
}