package com.eldarovich99.remote_assistant.presentation.view.chats

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_chats.*

class ChatsFragment : BaseFragment(){
    var adapterPosition = 0
    override fun dispatchKeyEvent(event: KeyEvent?){
        when (event?.keyCode){
            KeyEvent.KEYCODE_DPAD_CENTER -> {
                Toast.makeText(context, "Confirm button clicked", Toast.LENGTH_SHORT).show()
            }
            KeyEvent.KEYCODE_F1 -> {}
            KeyEvent.KEYCODE_F2 -> {}
            KeyEvent.KEYCODE_F3 -> {}
            KeyEvent.KEYCODE_F4 -> {}
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                Toast.makeText(context, "ViewPager changes fragment (left)", Toast.LENGTH_SHORT).show()
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                Toast.makeText(context, "ViewPager changes fragment (right)", Toast.LENGTH_SHORT).show()
            }
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                if (adapterPosition < chatsRecycler.adapter?.itemCount ?: 0)
                    chatsRecycler.scrollToPosition(++adapterPosition)
            }
            KeyEvent.KEYCODE_DPAD_UP -> {
                if (adapterPosition > 0)
                    chatsRecycler.scrollToPosition(--adapterPosition)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        chatsRecycler.adapter = ChatsAdapter()
        chatsRecycler.requestFocus()
        super.onViewCreated(view, savedInstanceState)
    }
}