package com.ivy.design

import com.ivy.design.api.systems.IvyWalletDesign

fun sampleAppDesignSystem() = object : IvyWalletDesign() {
    override fun context() = SampleContext()
}