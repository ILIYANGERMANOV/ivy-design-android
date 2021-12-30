package com.ivy.design.level2

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivy.design.R
import com.ivy.design.UI
import com.ivy.design.level0.White
import com.ivy.design.level1.IvyIcon
import com.ivy.design.level1.data.Background
import com.ivy.design.level1.data.background
import com.ivy.design.level1.data.clipBackground
import com.ivy.design.utils.IvyComponentPreview
import com.ivy.design.utils.padding

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    iconTint: Color = White,
    background: Background = Background.Solid(
        color = UI.colors.primary,
        shape = CircleShape,
        padding = padding(all = 8.dp)
    ),
    onClick: () -> Unit
) {
    IvyIcon(
        modifier = modifier
            .clipBackground(background)
            .clickable {
                onClick()
            }
            .background(background),
        icon = icon,
        tint = iconTint
    )
}

@Preview
@Composable
private fun Preview() {
    IvyComponentPreview {
        IconButton(
            icon = R.drawable.ic_baseline_add_24
        ) {

        }
    }
}