package com.ivy.design.api

import com.ivy.design.IvyContext
import com.ivy.design.Theme
import com.ivy.design.level0.IvyColors
import com.ivy.design.level0.IvyShapes
import com.ivy.design.level0.IvyTypography

interface IvyDesign {
    fun context(): IvyContext

    fun typography(): IvyTypography

    fun colors(theme: Theme): IvyColors

    fun shapes(): IvyShapes
}