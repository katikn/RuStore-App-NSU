package com.example.vkrustore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vkrustore.components.TopBar // <-- 1. Добавьте этот импорт

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenshotsPager(
    screenshots: List<Int>,
    startIndex: Int = 0,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val pagerState = rememberPagerState(
        initialPage = startIndex.coerceIn(0, (screenshots.size - 1).coerceAtLeast(0)),
        pageCount = { screenshots.size }
    )

    Box(modifier = modifier.fillMaxSize().background(Color.White)) {
        Column (modifier = Modifier.padding(8.dp)) {
            TopBar(navController = navController)
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = screenshots[page]),
                        contentDescription = "Screenshot $page",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
            }
        }

    }
}
