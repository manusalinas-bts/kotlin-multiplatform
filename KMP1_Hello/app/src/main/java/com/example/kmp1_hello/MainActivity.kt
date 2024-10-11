package com.example.kmp1_hello

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmp1_hello.ui.theme.KMP1_HelloTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var isTextClicked by remember { mutableStateOf(false) }
            //var nameText by remember { mutableStateOf("Hola!") }

            KMP1_HelloTheme {
                Card(
                    shape = RoundedCornerShape(80),
                    elevation = CardDefaults.cardElevation(15.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier
                        .padding(vertical = 50.dp)
                        .clickable {
                            isTextClicked = !isTextClicked
                            Log.i("Card", "Click on Card -> $isTextClicked")
                        }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = if (!isTextClicked) R.drawable.baseline_person_24 else R.drawable.baseline_accessible_24),
                            contentDescription = "Profile"
                        )

                        CustomText(
                            modifier = Modifier
                                .wrapContentSize()
                                .background(Color.Black),
                            text = if (isTextClicked) "Welcome back to the bench" else "Adivina que?",
                            fontSize = 25.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CustomText(modifier: Modifier, text: String, fontSize: TextUnit) {
    Text(
        text,
        color = Color.Yellow,
        fontSize = fontSize,
        modifier = modifier,
        fontWeight = FontWeight.Bold
    )
}

@Preview
@Composable
private fun CustomTextPreview() {
    CustomText(
        modifier = Modifier
            .wrapContentSize()
            .background(Color.Black)
            .clickable {
                Log.d("app", "Click on text")
            },
        text = "Welcome back to the bench",
        fontSize = 25.sp
    )
}