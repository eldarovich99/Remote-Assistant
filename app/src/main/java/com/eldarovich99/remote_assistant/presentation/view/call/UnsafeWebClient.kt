package com.eldarovich99.remote_assistant.presentation.view.call

import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import javax.inject.Inject

class UnsafeWebClient @Inject constructor(): WebViewClient(){
    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        handler?.proceed()
    }
}