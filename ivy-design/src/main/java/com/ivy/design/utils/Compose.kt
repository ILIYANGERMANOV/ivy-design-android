package com.ivy.design.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

@Composable
fun <T> densityScope(densityScope: @Composable Density.() -> T): T {
    return with(LocalDensity.current) { densityScope() }
}

fun Modifier.thenIf(
    condition: Boolean,
    otherModifier: Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        this.then(otherModifier())
    } else this
}

fun Modifier.thenWhen(
    logic: Modifier.() -> Modifier?
): Modifier {
    val otherModifier = logic()
    return if (otherModifier != null) {
        this.then(otherModifier)
    } else this
}