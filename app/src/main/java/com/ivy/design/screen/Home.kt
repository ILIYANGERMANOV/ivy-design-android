package com.ivy.design.screen

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivy.design.SampleAppPreview
import com.ivy.design.api.navigation
import com.ivy.design.l1_buildingBlocks.ColumnRoot
import com.ivy.design.l1_buildingBlocks.SpacerV
import com.ivy.design.l2_components.Button
import com.ivy.design.l3_ivyComponents.ScreenTitleLarge
import com.ivy.design.navigation.Screen

data class Home(
    val argTitle: String
) : Screen

@Composable
fun BoxWithConstraintsScope.HomeScreen(screen: Home) {
    UI(
        title = screen.argTitle
    )
}

@Composable
private fun UI(
    title: String
) {
    ColumnRoot(
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        SpacerV(height = 24.dp)

        ScreenTitleLarge(text = title)

        SpacerV(height = 32.dp)

        val navigation = navigation()
        Button(
            text = "Sample A"
        ) {
            navigation.navigateTo(SampleA)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SampleAppPreview {
        UI(title = "Home")
    }
}