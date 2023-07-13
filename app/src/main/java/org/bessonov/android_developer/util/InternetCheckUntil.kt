package org.bessonov.android_developer.util

import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

const val socketTimeoutMs = 1000

fun hasInternetConnection(): Boolean {
    return try {
        val socket = Socket()
        val googleHostName = "8.8.8.8"
        val port = 53
        val socketAddress = InetSocketAddress(googleHostName, port)
        socket.connect(socketAddress, socketTimeoutMs)
        socket.close()
        true
    } catch (e: IOException) {
        false
    }
}