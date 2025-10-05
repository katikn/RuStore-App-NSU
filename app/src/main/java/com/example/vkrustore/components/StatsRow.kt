package com.example.vkrustore.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vkrustore.AppInfo

@Composable
fun StatsRow(appInfo: AppInfo) {
    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item { StatItem("Рейтинг", appInfo.rating) }
        item { StatItem("Возраст", appInfo.ageLimit) }
        item { StatItem("Категория", appInfo.category) }
    }
}
