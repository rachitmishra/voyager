package com.voyager.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.voyager.R


@OptIn(ExperimentalTextApi::class)
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

@OptIn(ExperimentalTextApi::class)
val righteous = GoogleFont("Righteous")

@OptIn(ExperimentalTextApi::class)
val questrial = GoogleFont("Questrial")

@OptIn(ExperimentalTextApi::class)
val righteousFamily = FontFamily(
    androidx.compose.ui.text.googlefonts.Font(googleFont = righteous, fontProvider = provider)
)

@OptIn(ExperimentalTextApi::class)
val questrialFamily = FontFamily(
    androidx.compose.ui.text.googlefonts.Font(googleFont = questrial, fontProvider = provider)
)


// Set of Material typography styles to start with
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = questrialFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = questrialFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    titleLarge = TextStyle(
        fontFamily = righteousFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp
    )
)
