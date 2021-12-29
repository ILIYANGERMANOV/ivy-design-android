package com.ivy.design.level2

import android.content.res.ColorStateList
import android.text.InputType
import android.util.TypedValue
import android.view.inputmethod.EditorInfo
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
    inputType: IvyInputType = IvyInputType.SHORT_TEXT,
    onTextChanged: (String) -> Unit
) {
    AndroidView(
        modifier = modifier,
        factory = {
            EditText(it).apply {
                setText(initialText)
                backgroundTintList = ColorStateList.valueOf(Transparent.toArgb())
                setPadding(0, 0, 0, 0)
                setHint(hint)

                dynamicStyle(
                    textStyle = textStyle,
                    hintStyle = hintStyle,
                    inputType = inputType
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
                hintStyle = hintStyle,
                inputType = inputType
            )
        }
    )
}

private fun EditText.dynamicStyle(
    textStyle: TextStyle,
    hintStyle: TextStyle,
    inputType: IvyInputType
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

    this.inputType = when (inputType) {
        IvyInputType.SHORT_TEXT -> {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE
        }
        IvyInputType.LONG_TEXT -> {
            InputType.TYPE_TEXT_FLAG_MULTI_LINE or InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE
        }
        IvyInputType.NAMES -> {
            InputType.TYPE_TEXT_VARIATION_PERSON_NAME or InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE
        }
        IvyInputType.EMAIL -> {
            InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS or InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE
        }
        IvyInputType.PHONE -> {
            InputType.TYPE_CLASS_PHONE or InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE
        }
        IvyInputType.NUMBER -> {
            InputType.TYPE_CLASS_NUMBER
        }
        IvyInputType.PASSWORD -> {
            InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        IvyInputType.PASSWORD_NUMBER -> {
            InputType.TYPE_NUMBER_VARIATION_PASSWORD
        }
        IvyInputType.PASSWORD_VISIBLE -> {
            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }
        IvyInputType.PASSWORD_NUMBER_VISIBLE -> {
            InputType.TYPE_CLASS_NUMBER
        }
    }
    when (inputType) {
        IvyInputType.LONG_TEXT -> {
            isSingleLine = false
            imeOptions = EditorInfo.IME_FLAG_NO_ENTER_ACTION;
        }
        else -> {
            //do nothing
        }
    }
}

enum class IvyInputType {
    SHORT_TEXT,
    LONG_TEXT,
    NAMES,
    EMAIL,
    PHONE,
    NUMBER,
    PASSWORD,
    PASSWORD_NUMBER,
    PASSWORD_VISIBLE,
    PASSWORD_NUMBER_VISIBLE
}

enum class IvyIMEAction {
    DONE,
    NEXT
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