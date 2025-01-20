package com.example.tresenrayarobot

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket

class ConectarSocket {
    val port = 5858
    // Obrir el servidor
    val serverSocket = ServerSocket(port)


    // Obrir la porta a clients
    val clientSocket = serverSocket.accept()
    val output = PrintWriter(clientSocket.getOutputStream(), true)

    // Llegir el que diu el robot

    fun pedirPosicionActual():String{
        val input = BufferedReader(InputStreamReader(clientSocket.getInputStream(),"utf8"))
        val actualPose: String = input.readLine()
        return "Esta es mi posicion actual: $actualPose"
    }


    // actual pose to final pose

    //Tancar el server
    fun cerrarSocket(){
        serverSocket.close()
    }
}