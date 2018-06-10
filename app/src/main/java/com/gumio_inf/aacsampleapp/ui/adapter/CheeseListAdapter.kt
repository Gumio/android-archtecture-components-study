package com.gumio_inf.aacsampleapp.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.graphics.drawable.VectorDrawableCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.view.ViewGroupCompat
import android.support.v4.widget.TextViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gumio_inf.aacsampleapp.R
import com.gumio_inf.aacsampleapp.vo.Cheese
import com.gumio_inf.aacsampleapp.vo.Cheeses
import java.util.*

internal class CheeseListAdapter(
        context: Context,
        private val onCheeseSelected: (Long) -> Unit)
    : RecyclerView.Adapter<CheeseListAdapter.CheeseViewHolder>() {

    private val cheeses = ArrayList<Cheese>()

    private val favoriteDrawable = VectorDrawableCompat.create(context.resources,
            R.drawable.ic_favorite, context.theme)?.apply {
        DrawableCompat.setTint(this, Color.WHITE)
    }

    private val onClickListener = View.OnClickListener {
        onCheeseSelected(it.getTag(R.id.cheese_id) as Long)
    }

    fun setCheeses(cheeses: List<Cheese>?) {
        this.cheeses.clear()
        if (cheeses == null) {
            return
        }
        this.cheeses.addAll(cheeses)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CheeseViewHolder(parent, onClickListener)

    override fun onBindViewHolder(holder: CheeseViewHolder, position: Int) =
            holder.bind(cheeses[position], favoriteDrawable)

    override fun getItemCount() = cheeses.size

    class CheeseViewHolder(parent: ViewGroup, onClickListener: View.OnClickListener)
        : RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.cheese_list_item, parent, false)) {

        private var image = itemView.findViewById<ImageView>(R.id.image)
        private var name = itemView.findViewById<TextView>(R.id.name)

        init {
            itemView.setOnClickListener(onClickListener)
            ViewGroupCompat.setTransitionGroup(itemView as ViewGroup, true)
        }

        fun bind(cheese: Cheese, favoriteDrawable: Drawable?) {
            itemView.setTag(R.id.cheese_id, cheese.id)
            image.setImageResource(Cheeses.getDrawableForCheese(cheese.name))
            name.text = cheese.name
            TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(name,
                    null, null, if (cheese.favorite) favoriteDrawable else null, null)
        }

    }

}

