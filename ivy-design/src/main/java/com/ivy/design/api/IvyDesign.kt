package com.ivy.design.api

import com.ivy.design.IvyContext
import com.ivy.design.Theme
import com.ivy.design.l0_system.IvyColors
import com.ivy.design.l0_system.IvyShapes
import com.ivy.design.l0_system.IvyTypography

interface IvyDesign {
    fun context(): IvyContext

    fun typography(): IvyTypography

    fun colors(theme: Theme): IvyColors

    fun shapes(): IvyShapes
}