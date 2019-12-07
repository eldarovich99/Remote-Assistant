package com.eldarovich99.remote_assistant.presentation.ui

import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.eldarovich99.remote_assistant.R

class CloseConfirmationDialog(val fragment : Fragment) : Dialog(fragment.context!!) {
    fun get() {
        val parent = fragment.view?.findViewById(android.R.id.content) as? ViewGroup
        val view = LayoutInflater.from(context).inflate(R.layout.close_confirmation_dialog,
            parent, false)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)
        builder.create()
    }

}