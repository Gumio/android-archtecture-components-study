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
import com.gumio_inf.aacsampleapp.model.vo.Ramen
import com.gumio_inf.aacsampleapp.model.vo.Ramens
import java.util.*

internal class RamenListAdapter(
        context: Context,
        private val onRamenSelected: (Long) -> Unit)
    : RecyclerView.Adapter<RamenListAdapter.RamenViewHolder>() {

    private val ramens = ArrayList<Ramen>()

    private val favoriteDrawable = VectorDrawableCompat.create(context.resources,
            R.drawable.ic_favorite, context.theme)?.apply {
        DrawableCompat.setTint(this, Color.WHITE)
    }

    private val onClickListener = View.OnClickListener {
        onRamenSelected(it.getTag(R.id.ramen_id) as Long)
    }

    fun setRamens(ramens: List<Ramen>?) {
        this.ramens.clear()
        if (ramens == null) {
            return
        }
        this.ramens.addAll(ramens)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            RamenViewHolder(parent, onClickListener)

    override fun onBindViewHolder(holder: RamenViewHolder, position: Int) =
            holder.bind(ramens[position], favoriteDrawable)

    override fun getItemCount() = ramens.size

    class RamenViewHolder(parent: ViewGroup, onClickListener: View.OnClickListener)
        : RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.ramen_list_item, parent, false)) {

        private var image = itemView.findViewById<ImageView>(R.id.image)
        private var name = itemView.findViewById<TextView>(R.id.name)

        init {
            itemView.setOnClickListener(onClickListener)
            ViewGroupCompat.setTransitionGroup(itemView as ViewGroup, true)
        }

        fun bind(ramen: Ramen, favoriteDrawable: Drawable?) {
            itemView.setTag(R.id.ramen_id, ramen.id)
            image.setImageResource(Ramens.getDrawableForRamen(ramen.name))
            name.text = ramen.name
            TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(name,
                    null, null, if (ramen.favorite) favoriteDrawable else null, null)
        }

    }

}

