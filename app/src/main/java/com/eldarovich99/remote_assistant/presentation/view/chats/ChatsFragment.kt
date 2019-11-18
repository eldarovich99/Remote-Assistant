package com.eldarovich99.remote_assistant.presentation.view.chats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_chats.*

class ChatsFragment : BaseFragment(){
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