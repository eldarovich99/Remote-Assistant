package com.eldarovich99.remote_assistant.presentation.view.contacts

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.di.Scopes
import com.eldarovich99.remote_assistant.presentation.BaseFragment
import com.eldarovich99.remote_assistant.routing.ContactsScreen
import kotlinx.android.synthetic.main.fragment_chats.*
import toothpick.Toothpick
import javax.inject.Inject

class ContactsFragment : BaseFragment(){
    var adapterPosition = 0
    var shouldMove = true
    @Inject
    lateinit var adapter: ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toothpick.openScope(Scopes.CONTACTS_SCOPE) // It doesn't work because Inject happen before.
        // It is necessary to google it and find out how to deal with it
        return inflater.inflate(R.layout.fragment_chats, container, false)
    }

    override fun onDestroyView() {
        Toothpick.closeScope(Scopes.CONTACTS_SCOPE)
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            toolbar.title = getString(R.string.contacts)
            peopleRecycler.adapter = adapter // ContactsAdapter()
            peopleRecycler.requestFocus()
            peopleRecycler.addItemDecoration(
                DividerItemDecoration(
                    peopleRecycler.context,
                    RecyclerView.VERTICAL
                )
            )
            //bottomNavBar.selectButton(CONTACTS)
        super.onViewCreated(view, savedInstanceState)
    }
    override fun dispatchKeyEvent(event: KeyEvent?){
        when (event?.keyCode){
            KeyEvent.KEYCODE_DPAD_CENTER -> {
                Toast.makeText(context, "Confirm button clicked", Toast.LENGTH_SHORT).show()
            }
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                Toast.makeText(context, "ViewPager changes fragment (left)", Toast.LENGTH_SHORT).show()
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                router.navigateTo(ContactsScreen())
                Toast.makeText(context, "ViewPager changes fragment (right)", Toast.LENGTH_SHORT).show()
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

}