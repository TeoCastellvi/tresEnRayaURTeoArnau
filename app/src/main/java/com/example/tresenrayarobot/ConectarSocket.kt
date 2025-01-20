package com.example.tresenrayarobot

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket

class ConectarSocket {
    val port = 5858
    val serverSocket = ServerSocket(port)
    val clientSocket = serverSocket.accept()
    val output = PrintWriter(clientSocket.getOutputStream(), true)
    var posicionActual=""

    fun pedirPosicionActual() {
        val input = BufferedReader(InputStreamReader(clientSocket.getInputStream(), "utf8"))
        posicionActual= input.readLine()
    }

    fun enviarPosicion(x: Int, y: Int) {
        val posicio = "Posicio: $x,$y"
        output.println(posicio)
    }

    fun cerrarSocket() {
        clientSocket.close()
        serverSocket.close()
    }
}
