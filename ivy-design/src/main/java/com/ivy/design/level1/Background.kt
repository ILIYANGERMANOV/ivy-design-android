package com.ivy.design.level1

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ivy.design.level0.asBrush

sealed class Background {
    data class Solid(
        val color: Brush,
        val shape: Shape,
        val padding: IvyPadding
    ) : Background() {
        constructor(
            color: Color,
            shape: Shape,
            padding: IvyPadding
        ) : this(
            color = color.asBrush(),
            shape = shape,
            padding = padding
        )
    }

    data class Outlined(
        val color: Brush,
        val width: Dp = 1.dp,
        val shape: Shape,
        val padding: IvyPadding
    ) : Background() {
        constructor(
            color: Color,
            width: Dp = 1.dp,
            shape: Shape,
            padding: IvyPadding
        ) : this(
            color = color.asBrush(),
            width = width,
            shape = shape,
            padding = padding
        )
    }

    object None : Background()
}