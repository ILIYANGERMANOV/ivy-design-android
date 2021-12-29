package com.ivy.design

enum class Theme {
    LIGHT, DARK;

    fun inverted(): Theme {
        return when (this) {
            LIGHT -> DARK
            DARK -> LIGHT
        }
    }
}