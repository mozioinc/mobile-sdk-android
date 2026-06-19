package com.mozio.mobile.mobilesdkandroidsampleapp

import androidx.compose.ui.graphics.Color
import com.mozio.mobile.android.sdk.ui.ColorsConfig

internal val mozioYellow = Color(0xFFFFCC00)

internal val sampleLightColorsConfig = object : ColorsConfig {
    override val primary = mozioYellow
    override val onPrimary = Color(0xFF000000)
    override val secondary = mozioYellow
    override val onSecondary = Color(0xFF000000)
    override val foreground = Color(0xFF0B0C0C)
    override val background = Color(0xFFFFFFFF)
    override val surface = Color(0xFFF4F4F4)
    override val bottomSheetContainer = Color(0xFFFFFFFF)
    override val route = mozioYellow
    override val information = Color(0xFF0D84EB)
    override val error = Color(0xFFB60C0C)
    override val success = Color(0xFF4EB261)
    override val neutral1 = Color(0xFFE4E8EB)
    override val neutral2 = Color(0xFF97ABB6)
    override val neutral3 = Color(0xFF637280)
}
