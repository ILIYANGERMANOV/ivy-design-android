package com.ivy.design.level0

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.ivy.design.UI

fun TextStyle.colorAs(color: Color) = this.copy(color = color)

@Composable
fun TextStyle.style(
    color: Color = UI.colors.pureInverse,
    fontWeight: FontWeight = FontWeight.Bold,
    textAlign: TextAlign = TextAlign.Start
) = this.copy(
    color = color,
    fontWeight = fontWeight,
    textAlign = textAlign
)