package com.example.vkrustore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkrustore.ui.theme.subTitleText

@Composable
fun StatItem(title: String, value: String) {
    Column(
        modifier = Modifier
            .height(70.dp)
            .width(120.dp)
            .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(20.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(title, fontSize = 20.sp, style = subTitleText, color = Color(0xFF212121))
        Spacer(modifier = Modifier.height(8.dp))
        Text(value, fontSize = 15.sp, color = Color(0xFF212121))
    }
}