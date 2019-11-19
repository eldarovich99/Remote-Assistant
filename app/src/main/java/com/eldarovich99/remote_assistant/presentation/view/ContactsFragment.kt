package com.eldarovich99.remote_assistant.presentation.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.presentation.BaseFragment
import com.eldarovich99.remote_assistant.routing.ChatScreen
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment : BaseFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button2.requestFocus()
        button2.setOnClickListener {
            Toast.makeText(context, "button2", Toast.LENGTH_SHORT).show()
        }
        button3.setOnClickListener {
            Toast.makeText(context, "button3", Toast.LENGTH_SHORT).show()
        }
        button4.setOnClickListener {
            Toast.makeText(context, "button4", Toast.LENGTH_SHORT).show()
        }
        button5.setOnClickListener {
            Toast.makeText(context, "button5", Toast.LENGTH_SHORT).show()
        }
        button6.setOnClickListener {
            Toast.makeText(context, "button6", Toast.LENGTH_SHORT).show()
        }
        super.onViewCreated(view, savedInstanceState)
    }
    override fun dispatchKeyEvent(event: KeyEvent?) {
        when (event?.keyCode){
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                cicerone.router.navigateTo(ChatScreen())
                Toast.makeText(context, "ViewPager changes fragment (left)", Toast.LENGTH_SHORT).show()
            }
        }
    }

}