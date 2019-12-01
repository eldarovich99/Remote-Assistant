package com.eldarovich99.remote_assistant.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.di.Scopes
import kotlinx.android.synthetic.main.chat_toolbar.view.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

class ChatToolbar(context: Context, private val attrs: AttributeSet): LinearLayout(context, attrs){
    @Inject
    lateinit var cicerone: Cicerone<Router>

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var router: Router

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        val parentHeight = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(parentWidth, parentHeight)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
    init {
        View.inflate(context, R.layout.chat_toolbar, this)
        Toothpick.inject(this, Toothpick.openScopes(Scopes.APP_SCOPE, Scopes.ACTIVITY_SCOPE))
        backButton.setOnClickListener {
            router.exit()
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
}