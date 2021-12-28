package com.ivy.design.level1

import androidx.annotation.DrawableRes
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.ivy.design.UI

@Composable
fun IvyIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    tint: Color = UI.colors.pureInverse,
    contentDescription: String = "icon"
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = icon),
        contentDescription = contentDescription,
        tint = tint
    )
}