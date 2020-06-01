package com.takemyflight.pager2

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.item_discount_type.view.*

class DiscountTypeView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    var symbol: CharSequence?
        get() = image.text
        set(value) {
            image.text = value
        }

    var cardBackground: Int
        get() = -1
        set(value) {
            card.setBackgroundResource(value)
        }

    var symbolColor: Int
        get() = -1
        set(value) {
            image.setTextColor(value)
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.item_discount_type, this)
    }

}