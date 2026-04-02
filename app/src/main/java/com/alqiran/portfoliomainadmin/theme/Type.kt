package com.alqiran.portfoliomainadmin.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alqiran.portfoliomainadmin.R

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.rubik_semi_bold)),
        fontSize = 26.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.rubik_semi_bold)),
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.rubik_semi_bold)),
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_medium)),
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_regular)),
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_regular)),
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_medium)),
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_medium)),
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_regular)),
        fontSize = 11.sp,
        fontWeight = FontWeight.Normal
    )
)