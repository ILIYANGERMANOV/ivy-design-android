package com.ivy.design

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ivy.design.api.IvyUI
import com.ivy.design.api.systems.IvyWalletDesign
import com.ivy.design.level0.style
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
        Text(
            text = "Hello, Ivy Design!",
            style = UI.typo.h1.style(
                color = UI.colors.ivy
            )
        )
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