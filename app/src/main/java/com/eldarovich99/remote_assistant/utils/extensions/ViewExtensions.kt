package com.eldarovich99.remote_assistant.utils.extensions

import android.view.View

fun View.hide(){
    this.visibility = View.GONE
}

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.revertVisibility(){
    if (this.visibility == View.VISIBLE)
        this.visibility = View.GONE
    else
        this.visibility = View.VISIBLE
}