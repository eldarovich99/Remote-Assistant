package com.eldarovich99.remote_assistant.presentation.view.call

import android.hardware.Camera
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.di.Scopes
import com.eldarovich99.remote_assistant.presentation.BaseFragment
import com.eldarovich99.remote_assistant.presentation.ui.CloseConfirmationDialog
import com.eldarovich99.remote_assistant.presentation.ui.DialogResult
import com.eldarovich99.remote_assistant.utils.extensions.revertVisibility
import kotlinx.android.synthetic.main.fragment_call.*
import toothpick.Toothpick
import javax.inject.Inject




class CallFragment : BaseFragment(){
    var isChatVisible = true
    var camera: Camera? = null
    var lockObject = Object()
    @Inject
    lateinit var adapter : SingleChatAdapter
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
        Toothpick.openScope(Scopes.CALL_SCOPE) // It doesn't work because Inject happen before.
        return inflater.inflate(R.layout.fragment_call, container, false)
    }

    override fun onDestroyView() {
        Toothpick.closeScope(Scopes.CALL_SCOPE)
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callUpperBar.setOnBackButtonListener(this)
        chatRecycler.adapter = adapter
        showChatImageView.setOnClickListener { revertChatsVisibility() }
        launchCamera()
    }

    private fun revertChatsVisibility(){
        isChatVisible = !isChatVisible
        chatRecycler.revertVisibility()
        if (isChatVisible)
            showChatImageView.setImageDrawable(AppCompatResources.getDrawable(context!!, R.drawable.ic_keyboard_arrow_left_black))
        else
            showChatImageView.setImageDrawable(AppCompatResources.getDrawable(context!!, R.drawable.ic_keyboard_arrow_right))
    }

    fun launchCamera(){
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