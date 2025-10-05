package com.example.vkrustore

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vkrustore.ui.theme.VkRuStoreTheme
import com.example.vkrustore.ui.theme.bigHeader
import com.example.vkrustore.ui.theme.categoryTextStyle
import com.example.vkrustore.ui.theme.downloadButton
import com.example.vkrustore.ui.theme.smallHeader
import com.example.vkrustore.ui.theme.subTitleText
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

data class OnboardingPage(
    val title: String,
    val description: String,
    val imageRes: Int
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    val pages = listOf(
        OnboardingPage(
            title = "Добро пожаловать в RuStore",
            description = "Откройте для себя мир качественных приложений от проверенных разработчиков",
            imageRes = R.drawable.rustore
        ),
        OnboardingPage(
            title = "Гарантия безопасности",
            description = "Проверка приложений модерацией и современным антивирусом",
            imageRes = R.drawable.safety
        ),
        OnboardingPage(
            title = "Доступ к любым приложениям",
            description = "От мобильных банков до игровых хитов",
            imageRes = R.drawable.smartphone
        )
    )

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HorizontalPager(
                count = pages.size,
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .padding(top=70.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(240.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFF5F5F5)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = pages[page].imageRes),
                                contentDescription = pages[page].title,
                                modifier = Modifier
                                    .size(160.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = pages[page].title,
                        style = bigHeader,
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = pages[page].description,
                        color = Color(0xFF212121),
                        style = subTitleText,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .height(8.dp),
                activeColor = Color(0xFF0077FF),
                inactiveColor = Color(0xFFDEDEDE),
                indicatorWidth = 14.dp,
                indicatorHeight = 14.dp,
                spacing = 8.dp
            )

            Button(
                onClick = {
                    if (pagerState.currentPage == pages.size - 1) {
                        sharedPreferences.edit().putBoolean("onboarding_shown", true).apply()
                        navController.navigate("feed") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0077FF),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = if (pagerState.currentPage == pages.size - 1) "Начать просмотр" else "Далее",
                    fontSize = 16.sp,
                    style = downloadButton,
                    fontWeight = FontWeight.Medium
                )
            }

            if (pagerState.currentPage < pages.size - 1) {
                TextButton(
                    onClick = {
                        sharedPreferences.edit().putBoolean("onboarding_shown", true).apply()
                        navController.navigate("feed") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = "Пропустить",
                        color = Color(0xFF212121),
                        style = smallHeader,
                        fontSize = 14.sp
                    )
                }
            } else {
                Spacer(Modifier.size(56.dp))
            }
        }
    }
}

@Composable
fun OnboardingWrapper(
    navController: NavController = rememberNavController()
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val onboardingShown = sharedPreferences.getBoolean("onboarding_shown", false)
    OnboardingScreen(navController = navController)
}

@Preview
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreenPreview() {
    VkRuStoreTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            OnboardingScreen(navController = rememberNavController())
        }
    }
}
