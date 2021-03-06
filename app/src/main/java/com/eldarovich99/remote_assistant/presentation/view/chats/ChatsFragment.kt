package com.eldarovich99.remote_assistant.presentation.view.chats

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.eldarovich99.remote_assistant.di.modules.ChatsModule
import com.eldarovich99.remote_assistant.domain.models.ContactFull
import com.eldarovich99.remote_assistant.presentation.BaseFragment
import com.eldarovich99.remote_assistant.utils.extensions.revertVisibility
import kotlinx.android.synthetic.main.fragment_chats.*
import kotlinx.coroutines.*
import toothpick.Toothpick
import toothpick.ktp.KTP
import javax.inject.Inject

class ChatsFragment : BaseFragment(), ChatsView{
    var adapterPosition = 0
    var shouldMove = true
    var isChatVisible = true
    @Inject
    lateinit var adapter : ChatsAdapter
    @Inject
    lateinit var presenter: ChatsPresenter
    val searchScope = Dispatchers.IO + uiScope

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
                     adapterPosition+=1
                    peopleRecycler.findViewHolderForAdapterPosition(adapterPosition)?.itemView?.isSelected = true
                    peopleRecycler.scrollToPosition(adapterPosition)
                }
            }
            KeyEvent.KEYCODE_DPAD_UP -> {
                if (adapterPosition > 0) {
                    peopleRecycler.findViewHolderForAdapterPosition(adapterPosition)?.itemView?.isSelected = false
                    //shouldMove = shouldMove == false
                     adapterPosition-=1
                    peopleRecycler.findViewHolderForAdapterPosition(adapterPosition)?.itemView?.isSelected = true
                    peopleRecycler.scrollToPosition(adapterPosition)
                }
                else{
                    peopleRecycler.findViewHolderForAdapterPosition(adapterPosition)?.itemView?.isSelected = false
                    adapterPosition = -1
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        KTP.openScopes(Scopes.APP_SCOPE, Scopes.ACTIVITY_SCOPE, Scopes.CHATS_SCOPE).installModules(ChatsModule())
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chats, container, false)
    }

    override fun onDestroy() {
        Toothpick.closeScope(Scopes.CHATS_SCOPE)
        uiScope.cancel()
        super.onDestroy()
    }

    override fun onStart() {
        presenter.onAttach(this)
        super.onStart()
    }

    override fun onStop() {
        presenter.onDetach()
        super.onStop()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        CoroutineScope(uiScope+Dispatchers.IO).launch {
            presenter.getContacts()
        }
        callImageView.setOnClickListener {
            presenter.openCallScreen()
        }
            toolbar.title = getString(R.string.chats)
            peopleRecycler.requestFocus()
            peopleRecycler.adapter = adapter

            peopleRecycler.addItemDecoration(
                DividerItemDecoration(
                    peopleRecycler.context,
                    RecyclerView.VERTICAL
                )
            )
        showChatImageView.setOnClickListener {
            revertChatsVisibility()
        }
        searchEditText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                searchScope.cancelChildren()
                CoroutineScope(searchScope).launch {
                    delay(500)
                    val result = presenter.getSearchResult(searchEditText.text)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
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

    override fun showFailMessage(){
        Toast.makeText(this.context, "Не удалось выполнить запрос", Toast.LENGTH_SHORT).show()
    }

    override fun updateContacts(data: List<ContactFull>){
        adapter.updateData(data)
    }
}