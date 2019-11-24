package com.eldarovich99.remote_assistant.presentation.view.chats

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.presentation.BaseFragment
import com.eldarovich99.remote_assistant.routing.ContactsScreen
import com.eldarovich99.remote_assistant.routing.ScreenKeys.CHATS
import kotlinx.android.synthetic.main.fragment_chats.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job

@ExperimentalCoroutinesApi
class ChatsFragment : BaseFragment(){
    var adapterPosition = 0
    var shouldMove = true
   // val bottomNavBarObservable : Flow<Screen> by lazy { bottomNavBar.listenButtonClicked() }
    lateinit var buttonJob : Job

    override fun dispatchKeyEvent(event: KeyEvent?){
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
                Toast.makeText(context, "ViewPager changes fragment (left)", Toast.LENGTH_SHORT).show()
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                cicerone.router.navigateTo(ContactsScreen())
                Toast.makeText(context, "ViewPager changes fragment (right)", Toast.LENGTH_SHORT).show()
            }
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                if (adapterPosition < chatsRecycler.adapter?.itemCount ?: 0) {
                    chatsRecycler.findViewHolderForAdapterPosition(adapterPosition)?.itemView?.isSelected = false
                    shouldMove = shouldMove == false
                    if (shouldMove){ adapterPosition+=1
                    chatsRecycler.scrollToPosition(adapterPosition)}
                    chatsRecycler.findViewHolderForAdapterPosition(adapterPosition)?.itemView?.isSelected = true
                }
            }
            KeyEvent.KEYCODE_DPAD_UP -> {
                if (adapterPosition > 0) {
                    chatsRecycler.findViewHolderForAdapterPosition(adapterPosition)?.itemView?.isSelected = false
                    shouldMove = shouldMove == false
                    if (shouldMove){ adapterPosition-=1
                    chatsRecycler.scrollToPosition(adapterPosition)}
                    chatsRecycler.findViewHolderForAdapterPosition(adapterPosition)?.itemView?.isSelected = true
                }
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
        bottomNavBar.selectButton(CHATS)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
       // buttonJob.cancel()
        super.onPause()
    }
}