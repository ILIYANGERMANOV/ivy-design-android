package com.ivy.design.level1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ivy.design.UI
import com.ivy.design.utils.IvyComponentPreview
import com.ivy.design.utils.thenWhen

@Composable
fun HorizontalDivider(
    size: DividerSize = DividerSize.FillMax(
        padding = 16.dp
    ),
    width: Dp = 1.dp,
    color: Color = UI.colors.gray,
    shape: Shape = UI.shapes.rFull
) {
    Spacer(
        modifier = Modifier
            .thenWhen {
                when (size) {
                    is DividerSize.FillMax -> {
                        fillMaxWidth()
                            .padding(horizontal = size.padding)
                    }
                    is DividerSize.Fixed -> {
                        this.width(size.size)
                    }
                }
            }
            .clip(shape)
            .height(width)
            .background(color)
    )
}

@Composable
fun VerticalDivider(
    size: DividerSize = DividerSize.FillMax(
        padding = 16.dp
    ),
    width: Dp = 1.dp,
    color: Color = UI.colors.gray,
    shape: Shape = UI.shapes.rFull
) {
    Spacer(
        modifier = Modifier
            .thenWhen {
                when (size) {
                    is DividerSize.FillMax -> {
                        fillMaxHeight()
                            .padding(vertical = size.padding)
                    }
                    is DividerSize.Fixed -> {
                        this.height(size.size)
                    }
                }
            }
            .clip(shape)
            .width(width)
            .background(color)
    )
}

sealed class DividerSize {
    data class Fixed(val size: Dp) : DividerSize()

    data class FillMax(val padding: Dp) : DividerSize()
}

@Preview
@Composable
private fun PreviewHorizontalDivider_fillMax() {
    IvyComponentPreview {
        HorizontalDivider(
            size = DividerSize.FillMax(
                padding = 16.dp
            )
        )
    }
}

@Preview
@Composable
private fun PreviewHorizontalDivider_fixed() {
    IvyComponentPreview {
        HorizontalDivider(
            size = DividerSize.Fixed(
                size = 32.dp
            )
        )
    }
}

@Preview
@Composable
private fun PreviewVerticalDivider_fillMax() {
    IvyComponentPreview {
        VerticalDivider(
            size = DividerSize.FillMax(
                padding = 16.dp
            )
        )
    }
}

@Preview
@Composable
private fun PreviewVerticalDivider_fixed() {
    IvyComponentPreview {
        VerticalDivider(
            size = DividerSize.Fixed(
                size = 16.dp
            )
        )
    }
}