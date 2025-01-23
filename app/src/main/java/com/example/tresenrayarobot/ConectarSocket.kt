package com.example.tresenrayarobot

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class ConectarSocket {
    val port = 5858
    lateinit var serverSocket:ServerSocket
    lateinit var clientSocket:Socket
    //val output = PrintWriter(clientSocket.getOutputStream(), true)
    var posicionActual=""

    fun  pedirPosicionActual() {
        val input = BufferedReader(InputStreamReader(clientSocket.getInputStream(), "utf8"))
        posicionActual= input.readLine()
    }



    fun cerrarSocket() {
        clientSocket.close()
        serverSocket.close()
    }
    fun abrirSocket(){
        serverSocket = ServerSocket(port)
        clientSocket = serverSocket.accept()
    }
}