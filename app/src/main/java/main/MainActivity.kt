package main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text

class MainActivity: FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold {
                Column(modifier = Modifier.padding(16.dp).fillMaxWidth().fillMaxHeight()) {
                    Text(
                        text = "Hola IDara!",
                        modifier = Modifier.padding(all = 16.dp),
                        style = TextStyle(color = Color.Red, fontSize = 20.sp)
                    )
                }
            }
        }
    }
}