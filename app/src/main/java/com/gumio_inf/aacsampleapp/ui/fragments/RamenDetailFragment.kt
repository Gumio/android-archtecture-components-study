package com.gumio_inf.aacsampleapp.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.transition.Fade
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gumio_inf.aacsampleapp.R
import com.gumio_inf.aacsampleapp.model.vo.Ramens
import com.gumio_inf.aacsampleapp.ui.viewmodel.RamenDetailViewModel

class RamenDetailFragment : Fragment() {

    companion object {
        private const val ARG_RAMEN_ID = "ramen_id"

        fun newInstance(ramenId: Long) = RamenDetailFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG_RAMEN_ID, ramenId)
            }
        }
    }

    private lateinit var viewModel: RamenDetailViewModel

    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var favorite: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Fade()
    }

    private val RAMEN_DRAWABLES = intArrayOf(R.drawable.ramen_1, R.drawable.ramen_2, R.drawable.ramen_3, R.drawable.ramen_4)

    fun getDrawableForRamen(ramen: String): Int {
        return RAMEN_DRAWABLES[ramen.length % RAMEN_DRAWABLES.size]
    }

    val RAMENS = arrayOf("おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋", "おざき家", "まこと家", "極鶏", "ずんどう屋")

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val ramenId = arguments?.getLong(ARG_RAMEN_ID) ?: 0

        viewModel = ViewModelProviders.of(this).get(RamenDetailViewModel::class.java)
        viewModel.setRamenId(ramenId)
        viewModel.ramen.observe(this, Observer {
            it?.let { ramen ->
                // The name
                name.text = ramen.name
                // Show the image
                image.setImageResource(Ramens.getDrawableForRamen(ramen.name))
                //
                favorite.isEnabled = true
                if (ramen.favorite) {
                    favorite.setImageResource(R.drawable.ic_favorite)
                    favorite.contentDescription = getString(R.string.ramen_favorite_true)
                } else {
                    favorite.setImageResource(R.drawable.ic_favorite_border)
                    favorite.contentDescription = getString(R.string.ramen_favorite_mark)
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ramen_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        image = view.findViewById(R.id.image)
        name = view.findViewById(R.id.name)
        favorite = view.findViewById(R.id.favorite)
        favorite.setOnClickListener {
            favorite.isEnabled = false
            viewModel.toggleFavorite()
        }
    }

}