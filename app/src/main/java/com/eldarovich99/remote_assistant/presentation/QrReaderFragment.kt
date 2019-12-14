package com.eldarovich99.remote_assistant.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.PointF
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.routing.QRScreen
import kotlinx.android.synthetic.main.qr_fragment.*
import ru.terrakok.cicerone.Router

class QrReaderFragment : BaseFragment(), QRCodeReaderView.OnQRCodeReadListener {
    override fun dispatchKeyEvent(event: KeyEvent?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.qr_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        qrdecoderview?.run {
            setOnQRCodeReadListener(this@QrReaderFragment)

            // Use this function to enable/disable decoding
            setQRDecodingEnabled(true)

            // Use this function to change the autofocus interval (default is 5 secs)
            setAutofocusInterval(2000L)

            // Use this function to enable/disable Torch
            setTorchEnabled(true)

            // Use this function to set back camera preview
            setBackCamera()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        qrdecoderview.startCamera()
    }

    override fun onPause() {
        super.onPause()
        qrdecoderview.stopCamera()
    }

    override fun onQRCodeRead(text: String?, points: Array<out PointF>?) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    companion object {

        const val READ_QR_CODE = 1488
        const val QR_CODE_RESULT = "1337"

        fun start(fragment: BaseFragment, router: Router) {
            val hasPermission = checkPermissionsAndStartCamera(fragment)
            if (hasPermission) {
                openCamera(router)
            }
        }

        fun checkPermissionsAndStartCamera(fragment: BaseFragment): Boolean {
            fragment.context?.let {
                if (ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    if (fragment.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                        return false
                    } else {
                        fragment.requestPermissions(arrayOf(Manifest.permission.CAMERA), 1)
                    }
                } else {
                    return true
                }
            }

            return false
        }

        fun onRequestPermissionsResult(
            requestCode: Int,
            grantResults: IntArray, router: Router
        ) {
            if ((requestCode == 1) && (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                openCamera(router)
            }
        }

        private fun openCamera(router: Router) {
            router.navigateTo(QRScreen())
        }
    }
}