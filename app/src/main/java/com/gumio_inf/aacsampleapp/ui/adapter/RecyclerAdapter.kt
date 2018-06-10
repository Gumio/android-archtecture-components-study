package com.gumio_inf.aacsampleapp.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gumio_inf.aacsampleapp.R
import kotlinx.android.synthetic.main.recycler_item_view.view.*


class RecyclerAdapter(
        private val context: Context,
        private val itemClickListener: RecyclerViewHolder.ItemClickListener,
        private val itemList: List<String>
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    private var mRecyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null
    }

    override fun getItemCount() = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val mView = layoutInflater.inflate(R.layout.recycler_item_view, parent, false)

        mView.setOnClickListener { view ->
            mRecyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
            }
        }

        return RecyclerViewHolder(mView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.let {
            it.itemTextView.text = itemList[position]
            it.itemImageView.setImageResource(R.mipmap.ic_launcher)
        }
    }
}

class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // 独自に作成したListener
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    val itemTextView: TextView = view.itemTextView
    val itemImageView: ImageView = view.itemImageView

    init {
        // layoutの初期設定するときはココ
    }

}