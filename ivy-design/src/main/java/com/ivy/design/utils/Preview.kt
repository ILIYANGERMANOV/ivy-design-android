package com.ivy.design.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ivy.design.IvyApp
import com.ivy.design.IvyContext
import com.ivy.design.IvyTheme
import com.ivy.design.Theme


@Composable
fun IvyComponentPreview(theme: Theme = Theme.LIGHT, content: @Composable BoxScope.() -> Unit) {
    IvyAppPreview {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(IvyTheme.colors.pure),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
fun IvyAppPreview(
    theme: Theme = Theme.LIGHT,
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    val ivyContext = IvyContext()
    ivyContext.switchTheme(theme = theme)
    IvyApp(
        ivyContext = ivyContext,
        content = content
    )
}