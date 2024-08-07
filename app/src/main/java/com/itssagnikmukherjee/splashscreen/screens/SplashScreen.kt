package com.itssagnikmukherjee.splashscreen.screens

import android.view.inputmethod.InputContentInfo
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.itssagnikmukherjee.splashscreen.R

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SplashScreen() {
    val pagerState = rememberPagerState()
    val list = getList()
    Column {
        HorizontalPager(count = list.size, state = pagerState) {
            Column {
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