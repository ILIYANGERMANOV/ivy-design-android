package com.ivy.design.level1

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.ivy.design.utils.thenIf

@Composable
fun IvyText(
    modifier: Modifier = Modifier,
    text: String,
    typo: TextStyle,
    padding: IvyPadding? = null
) {
    Text(
        modifier = modifier
            .thenIf(padding != null) {
                this.padding(
                    ivyPadding = padding!!
                )
            },
        text = text,
        style = typo,
    )
}