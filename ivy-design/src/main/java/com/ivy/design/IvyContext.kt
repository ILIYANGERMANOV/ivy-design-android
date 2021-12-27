package com.ivy.design

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class IvyContext {
//    var currentScreen: Screen? by mutableStateOf(null)
//        private set

    var theme: Theme by mutableStateOf(Theme.LIGHT)
        private set

    var screenWidth: Int = -1
        get() {
            return if (field > 0) field else throw IllegalStateException("screenWidth not initialized")
        }
    var screenHeight: Int = -1
        get() {
            return if (field > 0) field else throw IllegalStateException("screenHeight not initialized")
        }

    fun switchTheme(theme: Theme) {
        this.theme = theme
    }
}