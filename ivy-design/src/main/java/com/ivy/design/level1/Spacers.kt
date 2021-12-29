package com.ivy.design.level1

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun SpacerV(
    height: Dp
) {
    Spacer(Modifier.height(height))
}

@Composable
fun SpacerH(
    width: Dp
) {
    Spacer(Modifier.width(width))
}

@Composable
fun RowScope.SpacerWeight(
    weight: Float
) {
    Spacer(Modifier.weight(weight))
}

@Composable
fun ColumnScope.SpacerWeight(
    weight: Float
) {
    Spacer(Modifier.weight(weight))
}