package com.ivy.design

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import com.ivy.design.api.IvyDesign
import com.ivy.design.api.NavigationRoot
import com.ivy.design.l0_system.Theme
import com.ivy.design.utils.IvyPreview


@Composable
fun SampleAppPreview(
    theme: Theme = Theme.LIGHT,
    design: IvyDesign = sampleAppDesignSystem(),
    Content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    IvyPreview(
        theme = theme,
        design = design,
    ) {
        NavigationRoot(navigation = sampleAppNavigation()) {
            Content()
        }
    }
}