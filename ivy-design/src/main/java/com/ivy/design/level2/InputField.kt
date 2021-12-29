package com.ivy.design.level2

import android.content.res.ColorStateList
import android.text.InputType
import android.util.TypedValue
import android.widget.EditText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.addTextChangedListener
import com.ivy.design.UI
import com.ivy.design.level0.Transparent
import com.ivy.design.level0.style
import com.ivy.design.utils.IvyComponentPreview

/**
 * Limitations:
 * - font cannot be set
 */
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    initialText: String = "",
    hint: String = "",
    textStyle: TextStyle = UI.typo.b1.style(
        color = UI.colors.pureInverse,
        textAlign = TextAlign.Start
    ),
    hintStyle: TextStyle = UI.typo.b1.style(
        color = Color.Gray,
        textAlign = TextAlign.Start
    ),
    onTextChanged: (String) -> Unit
) {
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
                    textStyle = textStyle,
                    hintStyle = hintStyle
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
                textStyle = textStyle,
                hintStyle = hintStyle
            )
        }
    )
}

private fun EditText.dynamicStyle(
    textStyle: TextStyle,
    hintStyle: TextStyle
) {
    setTextSize(TypedValue.COMPLEX_UNIT_SP, textStyle.fontSize.value)
    setTextColor(textStyle.color.toArgb())
    textAlignment = when (textStyle.textAlign) {
        TextAlign.Start -> EditText.TEXT_ALIGNMENT_VIEW_START
        TextAlign.Center -> EditText.TEXT_ALIGNMENT_CENTER
        TextAlign.End -> EditText.TEXT_ALIGNMENT_VIEW_END
        else -> EditText.TEXT_ALIGNMENT_VIEW_START
    }


    //hint text size cannot be set to EditText
    setHintTextColor(hintStyle.color.toArgb())
    //hint text alignment cannot be set to EditText
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