package com.ivy.design.screen

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivy.design.SampleAppPreview
import com.ivy.design.api.ivyContext
import com.ivy.design.l0_system.colorAs
import com.ivy.design.l1_buildingBlocks.*
import com.ivy.design.l2_components.*
import com.ivy.design.navigation.Screen
import com.ivy.design.utils.onEvent
import com.ivy.design.utils.padding

object SampleA : Screen

@Composable
fun BoxWithConstraintsScope.SampleAScreen(screen: SampleA) {
    UI()
}

@Composable
private fun UI() {
    ColumnRoot(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        SpacerV(24.dp)

//            SpacerKeyboardHeight(
//                animation = springBounceVerySlow()
//            )

        IvyText(
            text = "Sample A",
            typo = com.ivy.design.l0_system.UI.typo.h1.colorAs(com.ivy.design.l0_system.UI.colors.primary),
            padding = padding(horizontal = 16.dp)
        )

        SpacerV(12.dp)

        IvyText(
            text = "It's just the beginning...",
            typo = com.ivy.design.l0_system.UI.typo.b1,
            padding = padding(horizontal = 16.dp)
        )

        SpacerV(32.dp)

        var input by remember {
            mutableStateOf("")
        }

        IvyText(
            padding = padding(start = 16.dp),
            text = "Input:\n$input",
            typo = com.ivy.design.l0_system.UI.typo.b1
        )

        SpacerV(height = 16.dp)

        DividerH()

        val longTextFocus = InputFieldFocus()
        val shortTextFocus = InputFieldFocus()

        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            focus = longTextFocus,
            initialText = input,
            inputType = IvyInputType.LONG_TEXT,
            hint = "Input long text",
        ) {
            input = it
        }

        SpacerV(32.dp)

        DividerH()

        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            focus = shortTextFocus,
            inputType = IvyInputType.SHORT_TEXT,
            hint = "Input short text",
            imeAction = IvyImeAction.NEXT,
            onImeActionListener = {
                longTextFocus.requestFocus()
            }
        ) {
            //do nothing
        }

        onEvent {
            shortTextFocus.requestFocus()
        }

        SpacerV(24.dp)

        val ivyContext = ivyContext()
        Button(
            modifier = Modifier.padding(start = 16.dp),
            text = "Switch theme",
        ) {
            ivyContext.switchTheme(ivyContext.theme.inverted())
        }

        SpacerWeight(weight = 1f)
        SpacerKeyboardHeight()
        SpacerV(height = 48.dp) //extra scroll of 48.dp
    }
}

@Preview
@Composable
private fun Preview() {
    SampleAppPreview {
        UI()
    }
}