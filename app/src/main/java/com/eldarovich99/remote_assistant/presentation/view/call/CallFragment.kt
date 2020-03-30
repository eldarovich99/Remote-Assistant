package com.eldarovich99.remote_assistant.presentation.view.call

import android.hardware.Camera
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.di.Scopes
import com.eldarovich99.remote_assistant.di.modules.CallModule
import com.eldarovich99.remote_assistant.presentation.BaseFragment
import com.eldarovich99.remote_assistant.presentation.ui.CloseConfirmationDialog
import com.eldarovich99.remote_assistant.presentation.ui.DialogResult
import com.eldarovich99.remote_assistant.utils.extensions.revertVisibility
import kotlinx.android.synthetic.main.fragment_call.*
import kotlinx.coroutines.*
import toothpick.Toothpick
import toothpick.ktp.KTP
import java.io.IOException
import javax.inject.Inject


class CallFragment: BaseFragment(){
    var isChatVisible = true
    var camera: Camera? = null
    var lockObject = Object()
    var surfaceView: SurfaceView?=null
    var previewData = ByteArray(1280 * 720 * 3/2)
    var previewBuf = ByteArray(1280 * 720 * 3/2)

    @Inject
    lateinit var adapter : SingleChatAdapter
    @Inject
    lateinit var presenter: CallPresenter

    override suspend fun dispatchKeyEvent(event: KeyEvent?){
        when (event?.keyCode){
            KeyEvent.KEYCODE_DPAD_CENTER -> {
            }
            KeyEvent.KEYCODE_BACK ->{
                CloseConfirmationDialog(this@CallFragment).get(object : DialogResult{
                    override fun onDialogClosed(result: Boolean) {
                        if (result)
                            camera?.stopPreview()
                    }
                }).show()
            }
            KeyEvent.KEYCODE_F1 -> {
                Toast.makeText(context, "KEYCODE_F1", Toast.LENGTH_SHORT).show()}
            KeyEvent.KEYCODE_F2 -> {
                Toast.makeText(context, "KEYCODE_F2", Toast.LENGTH_SHORT).show()}
            KeyEvent.KEYCODE_F3 -> {
                Toast.makeText(context, "KEYCODE_F3", Toast.LENGTH_SHORT).show()}
            KeyEvent.KEYCODE_F4 -> {
                Toast.makeText(context, "KEYCODE_F4", Toast.LENGTH_SHORT).show()}
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                revertChatsVisibility()
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                revertChatsVisibility()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       KTP.openScopes(Scopes.APP_SCOPE, Scopes.ACTIVITY_SCOPE, Scopes.CALL_SCOPE)
           .installModules(CallModule(this))
           .inject(this)
        return inflater.inflate(R.layout.fragment_call, container, false)
    }

    override fun onDestroyView() {
        Toothpick.closeScope(Scopes.CALL_SCOPE)
        uiScope.cancel()
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callUpperBar.setOnBackButtonListener(this)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.stackFromEnd = true
        chatRecycler.layoutManager = layoutManager
        chatRecycler.adapter = adapter
        showChatImageView.setOnClickListener { revertChatsVisibility() }
        CoroutineScope(uiScope+Dispatchers.IO).launch {
            // TODO remove mock
            delay(2500)
            callUpperBar.launchTimer()
        }
        //launchCamera()
    }

    private fun revertChatsVisibility(){
        isChatVisible = !isChatVisible
        chatRecycler.revertVisibility()
        if (isChatVisible)
            showChatImageView.setImageDrawable(AppCompatResources.getDrawable(context!!, R.drawable.ic_keyboard_arrow_left_black))
        else
            showChatImageView.setImageDrawable(AppCompatResources.getDrawable(context!!, R.drawable.ic_keyboard_arrow_right))
    }



    private fun launchCamera(){
        surfaceView = SurfaceView(context)
        val holder = surfaceView!!.holder

        var previewCallback = object : Camera.PreviewCallback{
            override fun onPreviewFrame(p0: ByteArray?, p1: Camera?) {
                synchronized(lockObject){
                    previewData = p0!!
                }
                camera?.addCallbackBuffer(previewBuf)
            }
        }

        holder.addCallback(object: SurfaceHolder.Callback{
            override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
                camera!!.stopPreview()
                val params = camera!!.parameters
                /* Set preview resolution to 1080p */
                /* Set preview resolution to 1080p */
                params.setPreviewSize(1920, 1080)
                /* Set frame rate to 7.5fps */
                /* Set frame rate to 7.5fps */
                params.setPreviewFpsRange(7500, 7500)
                /* Reflect parameter change to camera device */
                /* Reflect parameter change to camera device */
                camera!!.parameters = params
                camera!!.addCallbackBuffer(previewData)
                camera!!.setPreviewCallbackWithBuffer(previewCallback)
                try {
                    camera!!.startPreview()
                } catch (e: RuntimeException) {
                    e.printStackTrace()
                }
            }

            override fun surfaceDestroyed(p0: SurfaceHolder?) {
                Log.d("SurfaceView", "surfaceDestroyed")
                camera?.stopPreview()
                camera?.release()
                camera = null
            }

            override fun surfaceCreated(p0: SurfaceHolder?) {
                Log.d("SurfaceView", "surfaceDestroyed")
                try {
                    camera = Camera.open()
                    camera?.setPreviewDisplay(holder)
                } catch (e: java.lang.RuntimeException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                camera!!.addCallbackBuffer(previewBuf)
                camera!!.setPreviewCallbackWithBuffer(previewCallback)
            }
        })
        val camera = Camera.open()
        val params = camera.parameters
        params.setEpsonCameraMode((Camera.Parameters.EPSON_CAMERA_MODE_SINGLE_THROUGH_720P))
        params.setPreviewFpsRange(15000, 15000)
        camera.parameters = params
        camera.startPreview()
        params.setPreviewSize(1280, 720)
        camera.setPreviewCallback { p0, p1 ->
            synchronized(lockObject){
                saveDepthMap(p0)
            }
        }
        /* camera.startDepthStreaming()
         camera.setDepthCallback(object: Camera.DepthCallback{
             override fun onDepthMap(data: ByteArray, camera: Camera) {
                 synchronized(lockObject) {
                     saveDepthMap(data);
                 }
             }
         })*/
    }

    fun saveDepthMap(data: ByteArray?){
        Log.d("Data", "data retrieved, size: ${data?.size}")
    }

/*    private fun getResolution(): IntArray? {
        if (com.epson.moverio.bt2000.sample.samplecamerapreview.MainActivity.RESOLUTIONS.size <= mResolutionIndex) {
            mResolutionIndex = 0
        } else if (mResolutionIndex < 0) {
            mResolutionIndex =
                com.epson.moverio.bt2000.sample.samplecamerapreview.MainActivity.RESOLUTIONS.size - 1
        }
        return com.epson.moverio.bt2000.sample.samplecamerapreview.MainActivity.RESOLUTIONS.get(
            mResolutionIndex
        )
    }*/

}