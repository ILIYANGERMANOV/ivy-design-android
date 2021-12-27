package com.ivy.design.level1

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun SpacerV(
    height: Dp
) {
    Spacer(Modifier.height(height = height))
}

@Composable
fun SpacerH(
    width: Dp
) {
    Spacer(Modifier.width(width = width))
}