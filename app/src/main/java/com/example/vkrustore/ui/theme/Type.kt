package com.example.vkrustore.ui.theme

import VKSans
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val bigHeader = TextStyle(
    fontSize = 36.sp,
    lineHeight = (36 * 1.10).sp,  // 110% от кегля
    letterSpacing = 0.sp,
    fontFamily = VKSans,
    fontWeight = FontWeight.Medium
)

val smallHeader = TextStyle(
        fontSize = 20.sp,
        lineHeight = (20 * 1.10).sp,  // 110% от кегля
        letterSpacing = 0.sp,
        fontFamily = VKSans,
        fontWeight = FontWeight.Medium
)

val subTitleText = TextStyle(
    fontSize = 14.sp,
    lineHeight = (14 * 1.10).sp,  // 110% от кегля
    letterSpacing = 0.sp,
    fontFamily = VKSans,
    fontWeight = FontWeight.Normal
)

val categoryTextStyle = TextStyle (
    fontSize = 14.sp,
    lineHeight = (14 * 1.10).sp,  // 110% от кегля
    letterSpacing = 0.sp,
    fontFamily = VKSans,
    fontWeight = FontWeight.Medium
)

val appTitleStyle = TextStyle (
    fontSize = 18.sp,
    lineHeight = (18 * 1.10).sp,  // 110% от кегля
    letterSpacing = 0.sp,
    fontFamily = VKSans,
    fontWeight = FontWeight.Medium
)

val downloadButton = TextStyle (
    fontSize = 15.sp,
    lineHeight = (15 * 1.10).sp,  // 110% от кегля
    letterSpacing = 0.sp,
    fontFamily = VKSans,
    fontWeight = FontWeight.Normal
)