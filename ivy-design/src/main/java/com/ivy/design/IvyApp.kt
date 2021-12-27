package com.ivy.design

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.google.accompanist.insets.ProvideWindowInsets

val LocalIvyContext = compositionLocalOf<IvyContext> { error("No LocalIvyContext") }

@Composable
fun IvyApp(
    ivyContext: IvyContext,
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    CompositionLocalProvider(
        LocalIvyContext provides ivyContext,
    ) {
        IvyTheme(theme = ivyContext.theme) {
            Surface(modifier = Modifier.fillMaxSize()) {
                ProvideWindowInsets {
                    BoxWithConstraints {
                        ivyContext.screenWidth = with(LocalDensity.current) {
                            maxWidth.roundToPx()
                        }
                        ivyContext.screenHeight = with(LocalDensity.current) {
                            maxHeight.roundToPx()
                        }
                        content()
                    }
                }
            }
        }
    }
}