package com.eldarovich99.remote_assistant

import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*

object AppConfig {
    val PUBLISHER_USERNAME = "hseprojectserver@yandex.ru"
    val PUBLISHER_PASSWORD = "alexYa89tgFpassMysql"

    fun getRtspAddress(): String?{
        val ip = getLocalIpAddress()
        if (ip != null)
            return "rtsp://$ip:1935/live/android_test"
        return null
    }

    fun getLocalIpAddress(): String? {
        try {
            val en: Enumeration<NetworkInterface> = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf: NetworkInterface = en.nextElement()
                val enumIpAddr: Enumeration<InetAddress> = intf.getInetAddresses()
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress: InetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress() && inetAddress is Inet4Address) {
                        return inetAddress.getHostAddress()
                    }
                }
            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }
        return null
    }
}