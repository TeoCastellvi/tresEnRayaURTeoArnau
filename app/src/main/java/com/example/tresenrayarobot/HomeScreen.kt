package com.example.tresenrayarobot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally, // Centra la columna horizontalmente
        verticalArrangement = Arrangement.Center // Centra el contenido verticalmente
    ) {
        Text(
            text = "Tres en ratlla",
            fontSize = 50.sp,
            color = Color.DarkGray,
        )
        Spacer(modifier = Modifier.height(116.dp))

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Fila 1
            Row(
                modifier = Modifier.fillMaxWidth(0.7f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Blue)
                )
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Blue)
                )
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Blue)
                )
            }

            // Fila 2
            Row(
                modifier = Modifier.fillMaxWidth(0.7f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Blue)
                )
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Blue)
                )
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Blue)
                )
            }

            // Fila 3
            Row(
                modifier = Modifier.fillMaxWidth(0.7f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Blue)
                )
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Blue)
                )
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Blue)
                )
            }
        }
    }
}
