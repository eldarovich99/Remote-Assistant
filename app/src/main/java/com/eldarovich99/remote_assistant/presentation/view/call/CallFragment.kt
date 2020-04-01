package com.eldarovich99.remote_assistant.presentation.view.call

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import com.eldarovich99.remote_assistant.AppConfig
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.di.Scopes
import com.eldarovich99.remote_assistant.di.modules.CallModule
import com.eldarovich99.remote_assistant.domain.models.Message
import com.eldarovich99.remote_assistant.presentation.BaseFragment
import com.eldarovich99.remote_assistant.presentation.ui.CloseConfirmationDialog
import com.eldarovich99.remote_assistant.presentation.ui.DialogResult
import com.eldarovich99.remote_assistant.streaming.Session
import com.eldarovich99.remote_assistant.streaming.SessionBuilder
import com.eldarovich99.remote_assistant.streaming.audio.AudioQuality
import com.eldarovich99.remote_assistant.streaming.gl.SurfaceView.ASPECT_RATIO_PREVIEW
import com.eldarovich99.remote_assistant.streaming.rtsp.RtspClient
import com.eldarovich99.remote_assistant.utils.extensions.hide
import com.eldarovich99.remote_assistant.utils.extensions.revertVisibility
import com.eldarovich99.remote_assistant.utils.extensions.show
import kotlinx.android.synthetic.main.fragment_call.*
import kotlinx.coroutines.*
import toothpick.Toothpick
import toothpick.ktp.KTP
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject


class CallFragment: BaseFragment(), RtspClient.Callback,
    Session.Callback, SurfaceHolder.Callback{
    var isChatVisible = true
    //var camera: Camera? = null
    val items = listOf<Message>()
    /*var lockObject = Object()
    var surfaceView: SurfaceView?=null
    var previewData = ByteArray(1280 * 720 * 3/2)
    var previewBuf = ByteArray(1280 * 720 * 3/2)*/
    @Inject
    lateinit var adapter : SingleChatAdapter
    @Inject
    lateinit var presenter: CallPresenter

    private var session: Session ?= null
    private var rtspClient: RtspClient?=null

    override suspend fun dispatchKeyEvent(event: KeyEvent?){
        when (event?.keyCode){
            KeyEvent.KEYCODE_DPAD_CENTER -> {
            }
            KeyEvent.KEYCODE_BACK ->{
                CloseConfirmationDialog(this@CallFragment).get(object : DialogResult{
                    override fun onDialogClosed(result: Boolean) {
                        /*if (result)
                            camera?.stopPreview()*/
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
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return inflater.inflate(R.layout.fragment_call, container, false)
    }

    override fun onDestroyView() {
        Toothpick.closeScope(Scopes.CALL_SCOPE)
        uiScope.cancel()
        rtspClient?.release();
        session?.release();
        surface?.holder?.removeCallback(this);
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callUpperBar.setOnBackButtonListener(this)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.stackFromEnd = true
        chatRecycler.layoutManager = layoutManager
        chatRecycler.adapter = adapter
        adapter.items = items
        if (adapter.items.isEmpty())
            emptyChatTextView.show() // TODO remove mock
        showChatImageView.setOnClickListener { revertChatsVisibility() }
        CoroutineScope(uiScope+Dispatchers.IO).launch {
            val duration = 2288
            val rotate = RotateAnimation(0f, 270f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotate.duration = duration.toLong()
            rotate.interpolator = LinearInterpolator()
            statusImageView.animation = rotate
            // TODO remove mock
            withContext(Dispatchers.Main){
                callUpperBar.setName("Никита Хлебко")
            }
            delay(2288)
            callUpperBar.launchTimer()
            withContext(Dispatchers.Main){
                statusImageView.setImageResource(0)
            }
        }
        setupCamera()
        //launchCamera()
    }

    private fun setupCamera(){
        //activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //// getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //activity?.requestWindowFeature(Window.FEATURE_NO_TITLE);
        surface.holder.addCallback(this)
        // Initialize RTSP client
        initRtspClient();
    }

    override fun onResume() {
        super.onResume()
        toggleStreaming()
    }

    override fun onPause() {
        super.onPause()
        toggleStreaming();
    }

    private fun initRtspClient() {
        // Configures the SessionBuilder
        session = SessionBuilder.getInstance()
            .setContext(activity?.application)
            .setAudioEncoder(SessionBuilder.AUDIO_NONE)
            .setAudioQuality(AudioQuality(8000, 16000))
            .setVideoEncoder(SessionBuilder.VIDEO_H264)
            .setSurfaceView(surface).setPreviewOrientation(0)
            .setCallback(this).build()

        // Configures the RTSP client
        rtspClient = RtspClient()
        rtspClient!!.setSession(session)
        rtspClient!!.setCallback(this)
        surface.setAspectRatioMode(ASPECT_RATIO_PREVIEW)
        val ip: String
        val port: String
        val path: String

        // We parse the URI written in the Editext
        val uri: Pattern = Pattern.compile("rtsp://(.+):(\\d+)/(.+)")
        val m: Matcher = uri.matcher(AppConfig.getRtspAddress())
        m.find()
        ip = m.group(1)
        port = m.group(2)
        path = m.group(3)
        rtspClient!!.setCredentials(
            AppConfig.PUBLISHER_USERNAME,
            AppConfig.PUBLISHER_PASSWORD
        )
        rtspClient!!.setServerAddress(ip, port.toInt())
        rtspClient!!.setStreamPath("/$path")
    }

    private fun toggleStreaming() {
        if (rtspClient?.isStreaming() == false) {
            // Start camera preview
            session?.startPreview()

            // Start video stream
            rtspClient?.startStream()
        } else {
            // already streaming, stop streaming
            // stop camera preview
            session?.stopPreview()

            // stop streaming
            rtspClient?.stopStream()
        }
    }



    private fun revertChatsVisibility(){
        isChatVisible = !isChatVisible
        chatRecycler.revertVisibility()
        if (isChatVisible) {
            if(adapter.items.isEmpty())
                emptyChatTextView.show()
            showChatImageView.setImageDrawable(
                AppCompatResources.getDrawable(
                    context!!,
                    R.drawable.ic_keyboard_arrow_left_black
                )
            )
        }
        else {
            if(adapter.items.isEmpty())
                emptyChatTextView.hide()
            showChatImageView.setImageDrawable(
                AppCompatResources.getDrawable(
                    context!!,
                    R.drawable.ic_keyboard_arrow_right
                )
            )
        }
    }

    override fun onRtspUpdate(message: Int, exception: Exception?) {
        when (message) {
            RtspClient.ERROR_CONNECTION_FAILED, RtspClient.ERROR_WRONG_CREDENTIALS -> {
                alertError(exception?.message)
                exception!!.printStackTrace()
            }
        }
    }

    private fun alertError(msg: String?) {
        val error = msg ?: "Unknown error: "
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setMessage(error).setPositiveButton("Ok",
            DialogInterface.OnClickListener { dialog, id -> })
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    override fun onSessionStarted() {}

    override fun onSessionStopped() { }

    override fun onPreviewStarted() {}

    override fun onSessionError(reason: Int, streamType: Int, e: Exception?) {
        if (e != null) {
            alertError(e.message);
            e.printStackTrace();
        }
    }

    override fun onSessionConfigured() {}

    override fun onBitrateUpdate(bitrate: Long) {}

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder?) {}

    override fun surfaceCreated(holder: SurfaceHolder?) { }


    /*private fun launchCamera(){
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
                *//* Set preview resolution to 1080p *//*
                *//* Set preview resolution to 1080p *//*
                params.setPreviewSize(1920, 1080)
                *//* Set frame rate to 7.5fps *//*
                *//* Set frame rate to 7.5fps *//*
                params.setPreviewFpsRange(7500, 7500)
                *//* Reflect parameter change to camera device *//*
                *//* Reflect parameter change to camera device *//*
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
        *//* camera.startDepthStreaming()
         camera.setDepthCallback(object: Camera.DepthCallback{
             override fun onDepthMap(data: ByteArray, camera: Camera) {
                 synchronized(lockObject) {
                     saveDepthMap(data);
                 }
             }
         })*//*
    }

    fun saveDepthMap(data: ByteArray?){
        Log.d("Data", "data retrieved, size: ${data?.size}")
    }

*//*    private fun getResolution(): IntArray? {
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