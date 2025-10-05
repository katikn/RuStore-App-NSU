package com.example.vkrustore.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkrustore.ui.theme.downloadButton
import com.example.vkrustore.ui.theme.subTitleText

@Composable
fun AppDescription(description: String, previewLength: Int = 100) {
    var expanded by remember { mutableStateOf(false) }

    val displayedText = if (expanded || description.length <= previewLength) {
        description
    } else {
        description.take(previewLength) + "..."
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
    ) {
        Text(
            text = displayedText,
            style = subTitleText,
            fontSize = 16.sp,
            color = Color.Black
        )

        if (description.length > previewLength) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                TextButton(onClick = { expanded = !expanded }) {
                    Text(
                        text = if (expanded) "Скрыть" else "Ещё",
                        color = Color.Blue,
                        style = downloadButton,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}