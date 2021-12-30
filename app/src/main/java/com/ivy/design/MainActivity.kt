package com.ivy.design

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivy.design.api.IvyUI
import com.ivy.design.api.ivyContext
import com.ivy.design.api.systems.IvyWalletDesign
import com.ivy.design.level0.colorAs
import com.ivy.design.level1.ColumnRoot
import com.ivy.design.level1.HorizontalDivider
import com.ivy.design.level1.IvyText
import com.ivy.design.level1.SpacerV
import com.ivy.design.level2.Button
import com.ivy.design.level2.InputField
import com.ivy.design.level2.IvyImeAction
import com.ivy.design.level2.IvyInputType
import com.ivy.design.utils.IvyPreview
import com.ivy.design.utils.padding

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IvyUI(
                design = design()
            ) {
                UI()
            }
        }
    }

    private fun design() = object : IvyWalletDesign() {
        override fun context() = SampleContext()
    }

    @Composable
    private fun UI() {
        ColumnRoot {
            SpacerV(24.dp)

            IvyText(
                text = "Hello, Ivy Design!",
                typo = UI.typo.h1.colorAs(UI.colors.primary),
                padding = padding(horizontal = 16.dp)
            )

            SpacerV(12.dp)

            IvyText(
                text = "It's just the beginning...",
                typo = UI.typo.b1,
                padding = padding(horizontal = 16.dp)
            )

            SpacerV(32.dp)

            var input by remember {
                mutableStateOf("")
            }

            IvyText(
                padding = padding(start = 16.dp),
                text = "Input:\n$input",
                typo = UI.typo.b1
            )

            SpacerV(height = 16.dp)

            HorizontalDivider()

            SpacerV(height = 16.dp)

            InputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                initialText = input,
                inputType = IvyInputType.LONG_TEXT,
                hint = "Input long text",
            ) {
                input = it
            }

            SpacerV(32.dp)

            HorizontalDivider()

            InputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                inputType = IvyInputType.SHORT_TEXT,
                hint = "Input short text",
                imeAction = IvyImeAction.NEXT,
                onImeActionListener = {
                    Toast.makeText(applicationContext, "IME action", Toast.LENGTH_SHORT)
                        .show()
                }
            ) {

            }

            SpacerV(24.dp)

            val ivyContext = ivyContext()
            Button(
                modifier = Modifier.padding(start = 16.dp),
                text = "Switch theme",
            ) {
                ivyContext.switchTheme(ivyContext.theme.inverted())
            }
        }
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        IvyPreview(
            design = design()
        ) {
            UI(

            )
        }
    }
}