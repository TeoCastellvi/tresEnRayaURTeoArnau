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
import androidx.compose.material3.Button
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
    var socket = ConectarSocket()  // Crear instància de la connexió amb el robot
    var turno by remember { mutableStateOf(true) }
    var winner by remember { mutableStateOf<Int?>(null) }
    val img = if (turno) {
        R.drawable.mando
    } else {
        R.drawable.robot
    }

    val boardState = remember {
        List(3) { MutableList(3) { mutableStateOf(R.drawable.blanc) } }
    }

    // Funció per comprovar el guanyador
    fun checkWinner(): Int? {
        // Comprovem les files horitzontals, verticals i diagonals (com abans)
        for (row in 0..2) {
            if (boardState[row][0].value != R.drawable.blanc &&
                boardState[row][0].value == boardState[row][1].value &&
                boardState[row][1].value == boardState[row][2].value) {
                return boardState[row][0].value // Guanyador
            }
        }

        for (col in 0..2) {
            if (boardState[0][col].value != R.drawable.blanc &&
                boardState[0][col].value == boardState[1][col].value &&
                boardState[1][col].value == boardState[2][col].value) {
                return boardState[0][col].value // Guanyador
            }
        }

        if (boardState[0][0].value != R.drawable.blanc &&
            boardState[0][0].value == boardState[1][1].value &&
            boardState[1][1].value == boardState[2][2].value) {
            return boardState[0][0].value // Guanyador
        }
        if (boardState[0][2].value != R.drawable.blanc &&
            boardState[0][2].value == boardState[1][1].value &&
            boardState[1][1].value == boardState[2][0].value) {
            return boardState[0][2].value
        }

        return null
    }

    fun enviarPosicioAlRobot(row: Int, col: Int) {
        socket.enviarPosicion(row, col)
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
            text = when {
                winner != null -> "Guanyador: ${if (winner == R.drawable.creu) "Tú" else "Robot"}"
                !turno -> "Es el torn del robot"
                else -> "Es el teu torn"
            },
            fontSize = 25.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.size(50.dp))

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
                                    if (winner == null && boardState[rowIndex][colIndex].value == R.drawable.blanc) {
                                        // Actualitza la casella
                                        boardState[rowIndex][colIndex].value = if (turno) {
                                            R.drawable.creu
                                        } else {
                                            R.drawable.rodona
                                        }
                                        // Envia la posició seleccionada al robot
                                        enviarPosicioAlRobot(rowIndex, colIndex)
                                        // Comprova si hi ha guanyador
                                        winner = checkWinner()
                                        // Canvia el torn
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

        // Botó per reiniciar el joc
        Button(
            onClick = {
                // Reinicia el tauler i el guanyador
                for (rowIndex in 0..2) {
                    for (colIndex in 0..2) {
                        boardState[rowIndex][colIndex].value = R.drawable.blanc
                    }
                }
                turno = true
                winner = null
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Reiniciar partida", fontSize = 20.sp)
        }
        Button(onClick = {socket.pedirPosicionActual()}) {
            Text(socket.posicionActual)
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
