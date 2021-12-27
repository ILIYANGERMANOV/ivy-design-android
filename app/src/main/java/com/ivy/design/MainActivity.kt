package com.ivy.design

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivy.design.api.IvyUI
import com.ivy.design.api.systems.IvyWalletDesign
import com.ivy.design.level0.colorAs
import com.ivy.design.level1.ColumnRoot
import com.ivy.design.level1.IvyText
import com.ivy.design.level1.SpacerV
import com.ivy.design.level1.padding
import com.ivy.design.utils.IvyPreview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IvyUI(
                design = design()
            ) {
                UI()
            }
        }
    }

    private fun design() = object : IvyWalletDesign() {
        override fun context() = SampleContext()
    }

    @Composable
    private fun UI() {
        ColumnRoot {
            SpacerV(24.dp)

            IvyText(
                text = "Hello, Ivy Design!",
                typo = UI.typo.h1.colorAs(UI.colors.ivy),
                padding = padding(horizontal = 16.dp)
            )

            SpacerV(12.dp)

            IvyText(
                text = "It's just the beginning...",
                typo = UI.typo.b1,
                padding = padding(horizontal = 16.dp)
            )
        }
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        IvyPreview(
            design = design()
        ) {
            UI(

            )
        }
    }
}