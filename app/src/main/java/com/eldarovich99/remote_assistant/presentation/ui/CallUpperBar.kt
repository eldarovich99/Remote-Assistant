package com.eldarovich99.remote_assistant.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.eldarovich99.remote_assistant.R
import kotlinx.android.synthetic.main.call_upper_bar.view.*

class CallUpperBar(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs){
    init {
        View.inflate(context, R.layout.call_upper_bar, this)
    }
    fun setName(text : String){
        companionName.text = text
    }
    fun launchTimer(){
        // TODO not implemented
    }
}