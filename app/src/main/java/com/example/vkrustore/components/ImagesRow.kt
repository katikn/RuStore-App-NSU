package com.example.vkrustore.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vkrustore.AppInfo

@Composable
fun ImagesRow(appInfo: AppInfo, onImageClick: (Int) -> Unit = {}) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(appInfo.images) { index, imageId ->
            androidx.compose.foundation.layout.Box(
                modifier = Modifier.clickable { onImageClick(index) }
            ) {
                ImageItem(imageId)
            }
        }
    }
}