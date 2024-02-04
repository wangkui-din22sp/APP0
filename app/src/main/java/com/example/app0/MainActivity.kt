package com.example.app0

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.app0.ui.theme.APP0Theme
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType






class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            APP0Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                   Bmi()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Bmi() {
    var heightInput: String by remember { mutableStateOf(value="") }
    var weightInput: String by remember { mutableStateOf(value="") }
    val height = heightInput.toFloatOrNull()?:0.0f
    val weight = weightInput.toFloatOrNull()?:0.0f
    val bmi = if (height > 0 && weight > 0 ) {
        weight / (height * height) * 10000
    } else {
        0.0f
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column( verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth().padding(16.dp)) {

        Text(text = stringResource(R.string.body_mass_index),
            fontSize = 24.sp, color = Color.Blue
            , textAlign = TextAlign.Center,
            modifier = Modifier.padding(top=16.dp, bottom=16.dp)
        )

        OutlinedTextField(
            value = heightInput,
            onValueChange = { heightInput = it.replace(',', '.') },
            label = { Text( text = stringResource(R.string.height_cm)  ) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
            ),

            keyboardActions = KeyboardActions(
                onDone = {
                    // close the keyboard
                    keyboardController?.hide()


                })



        )

        OutlinedTextField(
            value = weightInput,
            onValueChange = { weightInput = it.replace(',', '.') },
            label = { Text( text = stringResource(R.string.weight_kg) ) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),

            keyboardActions = KeyboardActions(
                onDone = {
                    // close the keyboard
                    keyboardController?.hide()


                })
        )

        Text(text = stringResource(R.string.result, String.format("%.2f", bmi).replace(',', '.')),
            fontSize = 24.sp,
            color = Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top=16.dp, bottom=16.dp)
        )





    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

        Bmi()
    }



