package com.eldarovich99.remote_assistant.presentation.view.chats

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.di.Scopes
import com.eldarovich99.remote_assistant.presentation.BaseFragment
import com.eldarovich99.remote_assistant.utils.extensions.revertVisibility
import kotlinx.android.synthetic.main.fragment_chats.*
import toothpick.Toothpick
import javax.inject.Inject

class ChatsFragment : BaseFragment(){
    var adapterPosition = 0
    var shouldMove = true
    var isChatVisible = true
    @Inject
    lateinit var adapter : ChatsAdapter

    override suspend fun dispatchKeyEvent(event: KeyEvent?){
        when (event?.keyCode){
            KeyEvent.KEYCODE_DPAD_CENTER -> {
                Toast.makeText(context, "Confirm button clicked", Toast.LENGTH_SHORT).show()
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
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                if (adapterPosition < peopleRecycler.adapter?.itemCount ?: 0) {
                    peopleRecycler.findViewHolderForAdapterPosition(adapterPosition)?.itemView?.isSelected = false
                    shouldMove = shouldMove == false
                    if (shouldMove){ adapterPosition+=1
                    peopleRecycler.scrollToPosition(adapterPosition)}
                    peopleRecycler.findViewHolderForAdapterPosition(adapterPosition)?.itemView?.isSelected = true
                }
            }
            KeyEvent.KEYCODE_DPAD_UP -> {
                if (adapterPosition > 0) {
                    peopleRecycler.findViewHolderForAdapterPosition(adapterPosition)?.itemView?.isSelected = false
                    shouldMove = shouldMove == false
                    if (shouldMove){ adapterPosition-=1
                    peopleRecycler.scrollToPosition(adapterPosition)}
                    peopleRecycler.findViewHolderForAdapterPosition(adapterPosition)?.itemView?.isSelected = true
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toothpick.openScope(Scopes.CHATS_SCOPE) // It doesn't work because Inject happen before.
        // It is necessary to google it and find out how to deal with it
        return inflater.inflate(R.layout.fragment_chats, container, false)
    }

    override fun onDestroyView() {
        Toothpick.closeScope(Scopes.CHATS_SCOPE)
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            toolbar.title = getString(R.string.chats)
            peopleRecycler.requestFocus()
            peopleRecycler.adapter = adapter
            //bottomNavBar.selectButton(CHATS)

            peopleRecycler.addItemDecoration(
                DividerItemDecoration(
                    peopleRecycler.context,
                    RecyclerView.VERTICAL
                )
            )
        showChatImageView.setOnClickListener {
            revertChatsVisibility()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    fun revertChatsVisibility(){
        isChatVisible = !isChatVisible
        searchIcon.revertVisibility()
        searchEditText.revertVisibility()
        peopleRecycler.revertVisibility()
        if (isChatVisible)
            showChatImageView.setImageDrawable(AppCompatResources.getDrawable(context!!, R.drawable.ic_keyboard_arrow_left_black))
        else
            showChatImageView.setImageDrawable(AppCompatResources.getDrawable(context!!, R.drawable.ic_keyboard_arrow_right))
    }
}