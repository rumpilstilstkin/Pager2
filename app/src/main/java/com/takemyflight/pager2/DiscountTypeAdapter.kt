package com.takemyflight.pager2

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2

class DiscountTypeAdapter(
    val list: List<String>,
    val pager: ViewPager2
): RecyclerView.Adapter<DiscountTypeAdapter.DiscountTypeViewHolder>() {

     var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscountTypeViewHolder {
        return DiscountTypeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DiscountTypeViewHolder, position: Int) {
        holder.bind(list[position % list.size], position == selectedPosition)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    class DiscountTypeViewHolder private constructor(
        root: View
    ) : RecyclerView.ViewHolder(root) {
        private val image: TextView = root.findViewById(R.id.image)
        private val card: CardView = root.findViewById(R.id.card)

        companion object {
            fun create(
                parent: ViewGroup
            ): DiscountTypeViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_discount_type, parent, false)
                return DiscountTypeViewHolder(view)
            }
        }

        fun bind(type: String, selected: Boolean) {
            image.text =  type
            if(selected){
                image.setTextColor(Color.WHITE)
                card.setCardBackgroundColor(Color.RED)
            }else{
                card.setCardBackgroundColor(Color.WHITE)
                image.setTextColor(Color.BLACK)
            }
        }
    }
}