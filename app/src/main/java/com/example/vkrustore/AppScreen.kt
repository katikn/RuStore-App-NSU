package com.example.vkrustore

import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.parcelize.Parcelize
import com.example.vkrustore.components.FullDescription
import com.example.vkrustore.components.ImagesRow
import com.example.vkrustore.components.MainInfoBlock
import com.example.vkrustore.components.StatsRow
import com.example.vkrustore.components.TopBar

@Parcelize
data class AppInfo(
    val name: String,
    val company: String,
    val iconResId: Int,
    val description: String,
    val rating: String,
    val ageLimit: String,
    val category: String,
    val images: List<Int>
) : Parcelable


@Composable
fun AppScreen(appInfo: AppInfo, navController: androidx.navigation.NavController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(8.dp)
    ) {
        TopBar(navController)
        MainInfoBlock(appInfo)
        StatsRow(appInfo)
        ImagesRow(
            appInfo = appInfo,
            onImageClick = { index ->
                navController.currentBackStackEntry?.savedStateHandle?.set("screens_images", appInfo.images.toIntArray())
                navController.currentBackStackEntry?.savedStateHandle?.set("screens_start", index)
                navController.navigate("screens")
            }
        )
        FullDescription(appInfo)
    }
}

