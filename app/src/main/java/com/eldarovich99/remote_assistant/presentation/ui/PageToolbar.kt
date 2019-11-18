package com.eldarovich99.remote_assistant.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.eldarovich99.remote_assistant.R

class PageToolbar(context: Context, private val attrs: AttributeSet): LinearLayout(context, attrs){
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        val parentHeight = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(parentWidth, parentHeight)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
    init {
        View.inflate(context, R.layout.page_toolbar, this)
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
}