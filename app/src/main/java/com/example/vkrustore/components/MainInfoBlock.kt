package com.example.vkrustore.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalDrawer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkrustore.AppInfo
import com.example.vkrustore.ui.theme.bigHeader
import com.example.vkrustore.ui.theme.downloadButton
import com.example.vkrustore.ui.theme.smallHeader
import com.example.vkrustore.ui.theme.subTitleText

@Composable
fun MainInfoBlock(appInfo: AppInfo) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .height(150.dp)
                .width(150.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = appInfo.iconResId),
                contentDescription = "App Icon",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(appInfo.name, style = bigHeader, fontSize = 30.sp)
            Spacer(modifier = Modifier.height(1.dp))
            Text(appInfo.company, style = subTitleText, modifier = Modifier.padding(start = 6.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    println("Спасибо за установку!")
                },
                modifier = Modifier
                    .height(34.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0077FF),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Скачать", style = downloadButton)
            }
        }
    }
}
