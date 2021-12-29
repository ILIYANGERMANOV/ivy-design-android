package com.ivy.design.level2

import android.content.res.ColorStateList
import android.text.InputType
import android.widget.EditText
import androidx.annotation.ColorInt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.addTextChangedListener
import com.ivy.design.UI
import com.ivy.design.level0.Transparent
import com.ivy.design.utils.IvyComponentPreview

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    initialText: String = "",
    hint: String = "",
    onTextChanged: (String) -> Unit
) {
    val textColor = UI.colors.pureInverse.toArgb()

    AndroidView(
        modifier = modifier,
        factory = {
            EditText(it).apply {
                inputType = InputType.TYPE_CLASS_TEXT
                setText(initialText)
                backgroundTintList = ColorStateList.valueOf(Transparent.toArgb())
                setPadding(0, 0, 0, 0)
                setHint(hint)

                dynamicStyle(
                    textColor = textColor
                )

                addTextChangedListener { editable ->
                    editable?.toString()?.let { text ->
                        onTextChanged(text)
                    }
                }
            }
        },
        update = {
            it.dynamicStyle(
                textColor = textColor
            )
        }
    )
}

private fun EditText.dynamicStyle(
    @ColorInt textColor: Int,
) {
    setTextColor(textColor)
    setHintTextColor(textColor)
}

@Preview
@Composable
private fun Preview() {
    IvyComponentPreview {
        InputField(
            initialText = "Test"
        ) {

        }
    }
}