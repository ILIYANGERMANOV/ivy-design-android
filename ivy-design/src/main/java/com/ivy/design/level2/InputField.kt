package com.ivy.design.level2

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.text.InputType
import android.util.Log
import android.util.TypedValue
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.addTextChangedListener
import com.ivy.design.UI
import com.ivy.design.level0.Purple1Dark
import com.ivy.design.level0.Purple1Light
import com.ivy.design.level0.Transparent
import com.ivy.design.level0.style
import com.ivy.design.utils.*
import kotlin.math.roundToInt


//TODO: Support setting focus
/**
 * Limitations:
 * - font cannot be set
 * - handles color must be set Theme XML `accentColor`
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
    imeAction: IvyImeAction = IvyImeAction.DONE,
    onImeActionListener: ((EditText) -> Unit)? = null,
    cursorColor: Color = UI.colors.pureInverse,
    highlightColor: Color = if (UI.colors.isLight) Purple1Light else Purple1Dark,
    focus: InputFieldFocus? = null,
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
                    cursorColor = cursorColor,
                    highlightColor = highlightColor,

                    textStyle = textStyle,
                    hintStyle = hintStyle,

                    inputType = inputType,
                    imeAction = imeAction,
                    onImeActionListener = onImeActionListener
                )

                addTextChangedListener { editable ->
                    editable?.toString()?.let { text ->
                        onTextChanged(text)
                    }
                }

                selectTextEnd()
            }
        },
        update = {
            it.dynamicStyle(
                cursorColor = cursorColor,
                highlightColor = highlightColor,

                textStyle = textStyle,
                hintStyle = hintStyle,

                inputType = inputType,
                imeAction = imeAction,
                onImeActionListener = onImeActionListener
            )

            //Log focus.triggerRecomposition so recomposition can be triggered
            Log.d("ivyInputField", "Triggering recomposition: ${focus?.triggerRecomposition}")
            if (focus?.consumeFocus() == true) {
                it.requestFocus()
                it.selectTextEnd()
                postDelayed(100) {
                    //ensure that the EditText is initialized
                    it.showKeyboard()
                }
            }
        }
    )
}

private fun EditText.dynamicStyle(
    highlightColor: Color,
    cursorColor: Color,

    textStyle: TextStyle,
    hintStyle: TextStyle,

    inputType: IvyInputType,
    imeAction: IvyImeAction,
    onImeActionListener: ((EditText) -> Unit)?
) {
    val originalSelection = this.selectionEnd

    this.highlightColor = highlightColor.toArgb()
    setCursorColor(cursorColor)

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
    imeOptions = when (imeAction) {
        IvyImeAction.DONE -> EditorInfo.IME_ACTION_DONE
        IvyImeAction.NEXT -> EditorInfo.IME_ACTION_NEXT
    }
    if (inputType != IvyInputType.LONG_TEXT) {
        //Make sure we don't break the default new line action
        setOnEditorActionListener { _, _, _ ->
            if (onImeActionListener != null) {
                onImeActionListener(this)
            } else {
                this.hideKeyboard()
            }
            true
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

    //restore original selection
    setSelection(originalSelection)
}

fun EditText.selectTextEnd() {
    setSelection(text.length)
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

enum class IvyImeAction {
    DONE,
    NEXT
}

class InputFieldFocus {
    private var requestFocus = false
    var triggerRecomposition: Int by mutableStateOf(0)
        private set

    fun consumeFocus(): Boolean {
        val shouldFocus = requestFocus
        requestFocus = false
        return shouldFocus
    }

    fun requestFocus() {
        this.requestFocus = true
        triggerRecomposition++
    }
}

fun EditText.setCursorColor(color: Color) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        textCursorDrawable = cursorDrawable(
            context = context,
            widthDp = 2.5f,
            color = color
        )
        //TODO: Fix bug where cursor color isn't updated after theme switch
    }
}

private fun cursorDrawable(
    context: Context,
    widthDp: Float = 3f,
    color: Color
): GradientDrawable {
    return GradientDrawable().apply {
//            <size android:width="3dp" />
//            <solid android:color="#FFFFFF"  />
        setSize(widthDp.dpToPx(context).roundToInt(), 16.dpToPx(context))
        setColor(color.toArgb())
    }
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