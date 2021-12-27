package com.ivy.design.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ivy.design.Theme
import com.ivy.design.UI
import com.ivy.design.api.IvyDesign
import com.ivy.design.api.IvyUI


@Composable
fun IvyComponentPreview(
    design: IvyDesign,
    theme: Theme = Theme.LIGHT,
    content: @Composable BoxScope.() -> Unit
) {
    IvyPreview(
        design = design,
        theme = theme
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(UI.colors.pure),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
fun IvyPreview(
    design: IvyDesign,
    theme: Theme = Theme.LIGHT,
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    design.context().switchTheme(theme = theme)
    IvyUI(
        design = design,
        Content = content
    )
}