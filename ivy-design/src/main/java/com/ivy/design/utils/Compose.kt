package com.ivy.design.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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

fun Modifier.consumeClicks() = clickableNoIndication {
    //consume click
}

fun Modifier.clickableNoIndication(
    onClick: () -> Unit
): Modifier = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick,
        role = null,
        indication = null
    )
}