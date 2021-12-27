package com.ivy.design.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

@Composable
fun <T> densityScope(densityScope: @Composable Density.() -> T): T {
    return with(LocalDensity.current) { densityScope() }
}

fun Modifier.thenIf(
    condition: Boolean,
    thanModifier: @Composable Modifier.() -> Modifier
): Modifier = composed {
    if (condition) {
        this.thanModifier()
    } else this
}