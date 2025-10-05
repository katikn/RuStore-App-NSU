package com.example.vkrustore

import CategoryScreen
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.vkrustore.ui.theme.VkRuStoreTheme
import com.example.vkrustore.ui.theme.bigHeader
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedGetBackStackEntry")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val onboardingShown = sharedPreferences.getBoolean("onboarding_shown", false)

        val startDestination = if (onboardingShown) "feed" else "onboarding"

        setContent {
            VkRuStoreTheme {
                val navController = rememberNavController()

                Scaffold() { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = startDestination,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        composable("onboarding") {
                            OnboardingScreen(navController)
                        }
                        composable("feed") {
                            MainScreen(navController)
                        }
                        composable("app") {
                            val appInfo = navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.get<AppInfo>("appInfo")

                            if (appInfo != null) {
                                AppScreen(appInfo = appInfo, navController = navController)
                            }
                        }
                        composable("screens") {
                            val parentEntry = navController.previousBackStackEntry
                            val images = parentEntry?.savedStateHandle?.get<IntArray>("screens_images")
                            val startIndex = parentEntry?.savedStateHandle?.get<Int>("screens_start") ?: 0

                            if (images != null && images.isNotEmpty()) {
                                ScreenshotsPager(
                                    screenshots = images.toList(),
                                    startIndex = startIndex,
                                    navController = navController
                                )
                            }
                        }
                        composable("category/{name}") { backStackEntry ->
                            val rawName = backStackEntry.arguments?.getString("name") ?: ""
                            val categoryName = try {
                                java.net.URLDecoder.decode(rawName, "UTF-8")
                            } catch (e: Exception) {
                                rawName
                            }
                            CategoryScreen(navController = navController, rawCategoryName = categoryName)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Приложения",
                    modifier = Modifier.padding(start = 20.dp, top = 5.dp, bottom = 10.dp),
                    style = bigHeader
                )
                Image(
                    painter = painterResource(id = R.drawable.rustore),
                    contentDescription = "logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(start = 110.dp)
                        .size(36.dp)
                )
            }
             CategoriesBar(navController)
             HomeScreen(navController)
        }
    }
}
