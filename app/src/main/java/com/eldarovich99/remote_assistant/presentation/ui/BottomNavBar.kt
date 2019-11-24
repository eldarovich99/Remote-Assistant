package com.eldarovich99.remote_assistant.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.di.Scopes
import com.eldarovich99.remote_assistant.routing.ScreenKeys
import com.eldarovich99.remote_assistant.routing.ScreenKeys.CHATS
import com.eldarovich99.remote_assistant.routing.ScreenKeys.CONTACTS
import kotlinx.android.synthetic.main.bottom_nav_bar.view.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

class BottomNavBar(context: Context, private val attrs: AttributeSet): LinearLayout(context, attrs) {
    @Inject
    lateinit var cicerone: Cicerone<Router>

    @Inject
    lateinit var navigator: Navigator
    private var selectedView: View? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        val parentHeight = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(parentWidth, parentHeight)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    init {
        View.inflate(context, R.layout.bottom_nav_bar, this)
        Toothpick.inject(this, Toothpick.openScopes(Scopes.APP_SCOPE, Scopes.ACTIVITY_SCOPE))
        // chatsButton.requestFocus()
        chatsButton.setOnClickListener {
            cicerone.router.navigateTo(ScreenKeys.getScreen(CHATS))
            Toast.makeText(context, "Chats", Toast.LENGTH_SHORT).show()
        }
        contactsButton.setOnClickListener {
            cicerone.router.navigateTo(ScreenKeys.getScreen(CONTACTS))
            Toast.makeText(context, "Contacts", Toast.LENGTH_SHORT).show()
        }
        /*val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PageToolbar)
        previousPageText.text = typedArray.getText(R.styleable.PageToolbar_previousPageText)
        headerText.text = typedArray.getText(R.styleable.PageToolbar_pageHeaderText)
        setUnderHeaderText(typedArray.getText(R.styleable.PageToolbar_pageUnderHeaderText))
        backButton.setOnClickListener {
            context.scanForActivity()?.onBackPressed()
        }
        headerText.textSize = typedArray.getFloat(R.styleable.PageToolbar_pageHeaderSize, 36f)
        typedArray.recycle()*/
    }

    override fun onDetachedFromWindow() {
        cicerone.navigatorHolder.removeNavigator()
        super.onDetachedFromWindow()
    }

    override fun onAttachedToWindow() {
        cicerone.navigatorHolder.setNavigator(navigator)
        super.onAttachedToWindow()
    }

    fun selectButton(key: String) {
        when (key) {
            CHATS -> {
                selectedView?.isSelected = false
                chatsButton.isSelected = true
                selectedView = chatsButton
            }
            CONTACTS -> {
                selectedView?.isSelected = false
                contactsButton.isSelected = true
                selectedView = contactsButton
            }
        }
    }

   /* fun listenButtonClicked() : Flow<Screen> = callbackFlow {

        chatsButton.setOnClickListener {
            offer(ScreenKeys.getScreen(CHATS))
        }
        contactsButton.setOnClickListener {
            offer(ScreenKeys.getScreen(CONTACTS))
        }
    }*/
}