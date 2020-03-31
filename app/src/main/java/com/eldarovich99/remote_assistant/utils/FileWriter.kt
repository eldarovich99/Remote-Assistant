package com.eldarovich99.remote_assistant.utils

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import okhttp3.ResponseBody
import java.io.*
import javax.inject.Inject

class FileWriter @Inject constructor(val context: Application) {
    fun writeResponseBodyToDisk(body: ResponseBody): Boolean {
        return try {
            // todo change the file location/name according to your needs
            val file =
                File(context.getExternalFilesDir(null)?.absolutePath + File.separator.toString() + "graph.pdf")
            if (!file.exists())
                file.createNewFile()
            if (!file.exists())
                file.createNewFile()
            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null
            try {
                val fileReader = ByteArray(4096)
                val fileSize = body.contentLength()
                var fileSizeDownloaded: Long = 0
                inputStream = body.byteStream()
                outputStream = FileOutputStream(file)
                while (true) {
                    val read: Int = inputStream.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()
                    Logger.log(this, "file downloaded: $fileSizeDownloaded of $fileSize")
                }
                outputStream.flush()
                true
            } catch (e: IOException) {
                false
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: IOException) {
            false
        }
    }

    fun openPDF(context: Context?, path: String) {
        val file = Environment.getExternalStoragePublicDirectory(path)
        Intent().apply {
            setDataAndType(Uri.fromFile(file), "application/pdf")
            //flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context?.startActivity(this)
        }
    }
}