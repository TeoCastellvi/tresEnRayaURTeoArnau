package com.example.tresenrayarobot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier
    ){
        Text(
            text = "Tres en ratlla",
            fontSize = 30.sp,
            color = Color.Gray,
        )

    }
}