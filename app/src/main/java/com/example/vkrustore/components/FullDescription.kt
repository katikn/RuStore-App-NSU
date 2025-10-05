package com.example.vkrustore.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkrustore.AppInfo
import com.example.vkrustore.ui.theme.bigHeader

@Composable
fun FullDescription(appInfo: AppInfo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Описание", style= bigHeader)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppDescription(description = appInfo.description)
        }
    }
}