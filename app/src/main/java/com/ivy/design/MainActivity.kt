package com.ivy.design

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ivy.design.api.IvyUI
import com.ivy.design.api.NavigationRoot
import com.ivy.design.navigation.Screen
import com.ivy.design.screen.Home
import com.ivy.design.screen.HomeScreen
import com.ivy.design.screen.SampleA
import com.ivy.design.screen.SampleAScreen

class MainActivity : ComponentActivity() {
    private val navigation = sampleAppNavigation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IvyUI(
                design = sampleAppDesignSystem()
            ) {
                NavigationRoot(
                    navigation = navigation
                ) { screen ->
                    UI(screen = screen)
                }
            }
        }

        navigation.navigateTo(Home("Hi, Ivy Design!"))
    }

    @Composable
    private fun BoxWithConstraintsScope.UI(screen: Screen?) {
        when (screen) {
            is Home -> HomeScreen(screen = screen)
            is SampleA -> SampleAScreen(screen = screen)
            else -> {
                //do nothing or show loading
            }
        }
    }


    @Preview
    @Composable
    fun DefaultPreview() {
        SampleAppPreview {
            UI(
                screen = Home("Hi, Ivy Design Preview!")
            )
        }
    }

    override fun onBackPressed() {
        if (!navigation.onBackPressed()) {
            super.onBackPressed()
        }
    }
}