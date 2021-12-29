package com.ivy.design.level2

import android.text.InputType
import android.widget.EditText
import androidx.annotation.ColorInt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.ivy.design.UI
import com.ivy.design.utils.IvyComponentPreview

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    initialText: String
) {
    val textColor = UI.colors.pureInverse.toArgb()

    AndroidView(
        modifier = modifier,
        factory = {
            EditText(it).apply {
                inputType = InputType.TYPE_CLASS_TEXT
                setText(initialText)
                style(
                    textColor = textColor
                )
            }
        },
        update = {
            it.style(
                textColor = textColor
            )
        }
    )
}

private fun EditText.style(
    @ColorInt textColor: Int,
) {
    setTextColor(textColor)
}

@Preview
@Composable
private fun Preview() {
    IvyComponentPreview {
        InputField(
            initialText = "Test"
        )
    }
}