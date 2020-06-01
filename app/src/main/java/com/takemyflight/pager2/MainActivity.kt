package com.takemyflight.pager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val list = listOf("A", "B", "C", "D")
        val adapter = DiscountTypeAdapter(list, pager)
        pager.adapter = adapter
        pager.clipChildren = false
        pager.clipToPadding = false
        pager.offscreenPageLimit = 5
        pager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        pager.registerOnPageChangeCallback(PageChangeCallback(adapter))

        val comPageTr = CompositePageTransformer()
        comPageTr.addTransformer(MarginPageTransformer(20))
        comPageTr.addTransformer { page, position ->
            val f = if(position >= -1 && position <=1) 1 - abs(position) else -1.1f
            page.scaleY = 1f + f * 0.2f
            page.scaleX = 1f + f * 0.2f
        }

        val card =
            resources.getDimensionPixelOffset(R.dimen.card_width).toFloat() + 2*resources.getDimensionPixelOffset(R.dimen.spaceM)

        comPageTr.addTransformer { page, position ->
            val offset = page.width/2 - card/2
            val myOffset: Float = position * -(2 * offset)
            if (pager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(pager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -myOffset
                } else {
                    page.translationX = myOffset
                }
            } else {
                page.translationY = myOffset
            }
        }

        pager.setPageTransformer(comPageTr)
        
        pager.postOnAnimation { pager.currentItem = 4000 }
    }
    
    inner class PageChangeCallback(
            val adapter: DiscountTypeAdapter
    ): ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            val oldPosition = adapter.selectedPosition
            adapter.selectedPosition = position
            adapter.notifyItemChanged(position)
            if (oldPosition != -1){
                adapter.notifyItemChanged(oldPosition)
            }
        }
    }
}