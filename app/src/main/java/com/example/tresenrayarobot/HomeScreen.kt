package com.example.tresenrayarobot

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    var turno by remember { mutableStateOf(true) }
    val img = if (turno) {
        R.drawable.mando
    } else {
        R.drawable.robot
    }

    val boardState = remember {
        List(3) { MutableList(3) { mutableStateOf(R.drawable.blanc) } }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tres en ratlla",
            fontSize = 50.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(40.dp))
        Image(painter = painterResource(id = img), contentDescription = null)
        Spacer(modifier = Modifier.size(5.dp))

        Text(
            text = if (!turno) "Es el torn del robot" else "Es el teu torn",
            fontSize = 25.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.size(50.dp))

        // Taulell de joc
        Box(
            modifier = Modifier
                .size(240.dp)
                .border(5.dp, Color.Black)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.size(240.dp)
            ) {
                for (rowIndex in 0..2) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.weight(1f)
                    ) {
                        for (colIndex in 0..2) {
                            GameCell(
                                currentImage = boardState[rowIndex][colIndex],
                                onCellClick = {
                                    // Comprova si la casella està buida abans de fer el canvi
                                    if (boardState[rowIndex][colIndex].value == R.drawable.blanc) {
                                        // Alterna entre creu i rodona
                                        boardState[rowIndex][colIndex].value = if (turno) {
                                            R.drawable.creu
                                        } else {
                                            R.drawable.rodona
                                        }

                                        // Alterna el torn
                                        turno = !turno
                                    }
                                },
                                isBordered = true,
                                isBottomBorder = rowIndex < 2,
                                isRightBorder = colIndex < 2
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.size(20.dp))

        // Botón para reiniciar el juego
        androidx.compose.material3.Button(
            onClick = {
                // Reinicia el estado del juego
                for (rowIndex in 0..2) {
                    for (colIndex in 0..2) {
                        boardState[rowIndex][colIndex].value = R.drawable.blanc
                    }
                }
                turno = true  // Reseteamos el turno a la primera persona
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Reiniciar partida", fontSize = 20.sp)
        }
    }
}

@Composable
fun GameCell(
    currentImage: MutableState<Int>,
    onCellClick: () -> Unit,
    isBordered: Boolean = false,
    isBottomBorder: Boolean = false,
    isRightBorder: Boolean = false
) {
    Image(
        painter = painterResource(id = currentImage.value),
        contentDescription = "Casilla del juego",
        modifier = Modifier
            .size(80.dp)
            .clickable { onCellClick() }
            .border(
                width = 3.dp,
                color = Color.Black,
                shape = RectangleShape
            )
            .let { baseModifier ->

                var updatedModifier = baseModifier
                if (isBottomBorder) {
                    updatedModifier = updatedModifier.border(
                        width = 3.dp,
                        color = Color.Black,
                        shape = RectangleShape
                    )
                }
                if (isRightBorder) {
                    updatedModifier = updatedModifier.border(
                        width = 3.dp,
                        color = Color.Black,
                        shape = RectangleShape
                    )
                }
                updatedModifier
            }
    )
}
