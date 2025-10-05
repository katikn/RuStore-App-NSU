package com.example.vkrustore.components

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@SuppressLint("ContextCastToActivity")
@Composable
fun TopBar(navController: NavController, showBack: Boolean = navController.previousBackStackEntry != null) {
    val activity = (LocalContext.current as? Activity)
    Row(
        modifier = Modifier.width(40.dp).padding(top = 0.dp, bottom = 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showBack) {
            IconButton(
                onClick = {
                    val handled = navController.navigateUp()
                    if (!handled) activity?.finish()
                },
                modifier = Modifier.size(30.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
            }
        }
    }
}
