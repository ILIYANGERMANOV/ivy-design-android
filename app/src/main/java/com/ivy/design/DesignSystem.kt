package com.ivy.design

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import com.ivy.design.api.IvyDesign
import com.ivy.design.api.systems.IvyWalletDesign
import com.ivy.design.l0_system.Theme
import com.ivy.design.utils.IvyPreview

fun sampleDesignSystem() = object : IvyWalletDesign() {
    override fun context() = SampleContext()
}

@Composable
fun SampleAppPreview(
    theme: Theme = Theme.LIGHT,
    design: IvyDesign = sampleDesignSystem(),
    Content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    IvyPreview(
        theme = theme,
        design = design,
        Content = Content
    )
}