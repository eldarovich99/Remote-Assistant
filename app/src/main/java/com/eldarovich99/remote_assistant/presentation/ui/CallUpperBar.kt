package com.eldarovich99.remote_assistant.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.utils.extensions.hide
import com.eldarovich99.remote_assistant.utils.extensions.show
import kotlinx.android.synthetic.main.call_upper_bar.view.*
import kotlinx.coroutines.*
import java.text.SimpleDateFormat

class CallUpperBar(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs){
    val job = SupervisorJob()
    val uiScope = job + Dispatchers.Main

    init {
        View.inflate(context, R.layout.call_upper_bar, this)
    }
    fun setOnBackButtonListener(fragment: Fragment){
        backButton.setOnClickListener {
            CloseConfirmationDialog(fragment).get(object : DialogResult{
                override fun onDialogClosed(result: Boolean) {}
            }).show()
        }
    }
    fun setName(text : String){
        companionName.text = text
    }

    fun launchTimer(){
        var time = 0
        val timeFormat = SimpleDateFormat("mm:ss")
        CoroutineScope(uiScope + Dispatchers.IO).launch {
            while(true) {
                withContext(Dispatchers.Main) {
                    callLength.text = timeFormat.format(time * 1000)
                }
                time++
                delay(1000)
            }
        }
        CoroutineScope(uiScope + Dispatchers.IO).launch {
            while(true) {
                withContext(Dispatchers.Main) {
                    if (indicatorImageView.visibility == View.VISIBLE)
                        indicatorImageView.hide()
                    else
                        indicatorImageView.show()
                }
                delay(750)
            }
        }
    }

    override fun onDetachedFromWindow() {
        uiScope.cancel()
        super.onDetachedFromWindow()
    }
}