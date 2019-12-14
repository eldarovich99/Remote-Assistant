package com.eldarovich99.remote_assistant.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.di.Scopes
import kotlinx.android.synthetic.main.close_confirmation_dialog.view.*
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

class CloseConfirmationDialog(val fragment : Fragment){
    @Inject
    lateinit var router : Router
    //var dialog : AlertDialog?=null
    fun get() : AlertDialog {
        fragment.context?.let{
            val parent = fragment.view?.findViewById(android.R.id.content) as? ViewGroup
            val view = LayoutInflater.from(fragment.context).inflate(R.layout.close_confirmation_dialog,
                parent, false)
            val builder = AlertDialog.Builder(fragment.context!!)
            builder.setView(view)
            val dialog = builder.create()
            Toothpick.inject(this, Toothpick.openScopes(Scopes.APP_SCOPE, Scopes.ACTIVITY_SCOPE, Scopes.CHATS_SCOPE))
            view.cancelDialogButton.setOnClickListener {
                dialog.dismiss()
            }
            view.endDialogButton.setOnClickListener {
                dialog.dismiss()
                router.exit()
            }
            return dialog
        }
        throw Exception("Fragment context is null")
    }
}