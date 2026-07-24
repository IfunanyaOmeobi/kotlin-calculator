package com.cindy.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cindy.calculator.ui.theme.CalculatorTheme
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {

    // using of state variables (expression and result) to hold the expression being entered
    // and the result of calculations.
    var expression by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }


   // functions defined inside for manipulating the expression and result
    fun setExpression(value: String) {
        expression = value
        if (expression.isEmpty()) {
            result = ""
        }
    }

    fun updateExpression(value: String) {
        expression += when (value) {
            "x" -> "*"
            "÷" -> "÷"
            else -> value
        }
    }

    fun clearExpression() {
        expression = ""
        result = ""
    }


    // this column creates a layout for displaying the expression & result in the calculator screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {

        // this column - is a nested column which organize the layout vertically
        Column(
            modifier = modifier
                .weight(1f)
                .padding(16.dp)
        ) {

            // Spacer - push down the expression & result for displaying in normal position
            Spacer(modifier = Modifier.weight(2f))

            Text(
                modifier = modifier.fillMaxWidth(),
                text = expression,
                style = TextStyle(fontSize = 50.sp, color = Color.White, textAlign = TextAlign.End)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = modifier.fillMaxWidth(),
                text = result,
                style = TextStyle(fontSize = 35.sp, color = Color.White, textAlign = TextAlign.End)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.2f)
                .background(color = Color.Gray.copy(alpha = 0.2f))
        ) {

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = modifier.fillMaxWidth()) {
                    CalculatorButton(
                        text = "c",
                        modifier = modifier.weight(1f),
                        onClick = { clearExpression() },
                        textColor = Color.Blue
                    )
                    CalculatorButton(
                        text = "÷",
                        modifier = modifier.weight(1f),
                        onClick = { updateExpression("÷") },
                        textColor = Color.Blue
                    )
                    CalculatorButton(
                        text = "x",
                        modifier = modifier.weight(1f),
                        onClick = { updateExpression("x") },
                        textColor = Color.Blue
                    )
                    CalculatorButton(
                        text = "⌫",
                        modifier = modifier.weight(1f),
                        onClick = { delCharacter(expression, ::setExpression) },
                        textColor = Color.Blue
                    )
                }


                Row(modifier = modifier.fillMaxWidth()) {
                    CalculatorButton(
                        text = "7",
                        modifier = modifier.weight(1f),
                        onClick = { expression += it },
                        textColor = Color.White
                    )
                    CalculatorButton(
                        text = "8",
                        modifier = modifier.weight(1f),
                        onClick = { expression += it },
                        textColor = Color.White
                    )
                    CalculatorButton(
                        text = "9",
                        modifier = modifier.weight(1f),
                        onClick = { expression += it },
                        textColor = Color.White
                    )
                    CalculatorButton(
                        text = "-",
                        modifier = modifier.weight(1f),
                        onClick = { expression += it },
                        textColor = Color.Blue
                    )
                }


                Row(modifier = modifier.fillMaxWidth()) {
                    CalculatorButton(
                        text = "4",
                        modifier = modifier.weight(1f),
                        onClick = { expression += it },
                        textColor = Color.White
                    )
                    CalculatorButton(
                        text = "5",
                        modifier = modifier.weight(1f),
                        onClick = { expression += it },
                        textColor = Color.White
                    )
                    CalculatorButton(
                        text = "6",
                        modifier = modifier.weight(1f),
                        onClick = { expression += it },
                        textColor = Color.White
                    )
                    CalculatorButton(
                        text = "+",
                        modifier = modifier.weight(1f),
                        onClick = { expression += it },
                        textColor = Color.Blue
                    )
                }


                Row(modifier = modifier.fillMaxWidth()) {
                    CalculatorButton(
                        text = "1",
                        modifier = modifier.weight(1f),
                        onClick = { expression += it },
                        textColor = Color.White
                    )
                    CalculatorButton(
                        text = "2",
                        modifier = modifier.weight(1f),
                        onClick = { expression += it },
                        textColor = Color.White
                    )
                    CalculatorButton(
                        text = "3",
                        modifier = modifier.weight(1f),
                        onClick = { expression += it },
                        textColor = Color.White
                    )
                    CalculatorButton(
                        text = "=",
                        modifier = modifier
                            .weight(1f),
                        onClick = {
                            if (expression.isEmpty()) return@CalculatorButton
                            result = solveExpression(expression)
                        },
                        textColor = Color.White
                    )
                }


                Row(modifier = modifier.fillMaxWidth()) {
                    CalculatorButton(
                        text = "%",
                        modifier = modifier.weight(1f),
                        onClick = { expression += it },
                        textColor = Color.White
                    )
                    CalculatorButton(
                        text = "0",
                        modifier = modifier.weight(1f),
                        onClick = { expression += it },
                        textColor = Color.White
                    )
                    CalculatorButton(
                        text = ".",
                        modifier = modifier.weight(1f),
                        onClick = { expression += it },
                        textColor = Color.White
                    )
                    CalculatorButton(
                        text = "",
                        modifier = modifier.weight(1f),
                        onClick = { expression += it },
                        textColor = Color.White
                    )

                    // NB: onClick = { expression += it } means, "When a button is clicked,
                // add whatever is written on the button to what's already shown on the screen.
                }
            }
        }

    }
}

/* solveExpression - this function tries to solve the mathematical expression provided as a string.
If successful, it returns the result as a string. If there's an error during the evaluation, it
prints the error message and returns "Invalid expression*/

fun solveExpression(expression: String): String {
    return try {
        val result = ExpressionBuilder(expression.replace("÷", "/"))
            .build()
            .evaluate()
            .toString()
            .replace(".0", "")

        result
    } catch (e: Exception) {
        e.printStackTrace()
        "Invalid expression"
    }
}


//delCharacter- it deletes the last character and update the screen with the remaining new strings
fun delCharacter(expression: String, setResult: (String) -> Unit): String {
    val newExpression = if (expression.isNotEmpty()) {
        expression.substring(0, expression.length - 1)
    } else {
        ""
    }
    setResult(newExpression)
    return newExpression

}


@Composable
fun CalculatorButton(
    modifier: Modifier = Modifier,
    text: String = "0",
    onClick: (String) -> Unit,
    textColor: Color
) {
    val buttonShape = if (text == "=") {
        MaterialTheme.shapes.extraLarge
    } else {
        RoundedCornerShape(2f)
    }

    val buttonColor = if (text == "=") {
        Color.Blue
    } else {
        Color.Transparent
    }

    Button(
        modifier = modifier
            .size(80.dp)
            .clip(buttonShape)
            .padding(6.dp),

        onClick = { onClick(text) },
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = Color.Black
        )
    ) {

        Text(text = text, style = TextStyle(fontSize = 30.sp, color = textColor))

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalPreview() {
    CalculatorTheme {
        CalculatorScreen()
    }
}