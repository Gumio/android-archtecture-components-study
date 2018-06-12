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
import com.gumio_inf.aacsampleapp.ui.viewmodel.CheeseDetailViewModel
import com.gumio_inf.aacsampleapp.model.vo.Cheeses

class CheeseDetailFragment : Fragment() {

    companion object {
        private const val ARG_CHEESE_ID = "cheese_id"

        fun newInstance(cheeseId: Long) = CheeseDetailFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG_CHEESE_ID, cheeseId)
            }
        }
    }

    private lateinit var viewModel: CheeseDetailViewModel

    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var favorite: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Fade()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val cheeseId = arguments?.getLong(ARG_CHEESE_ID) ?: 0

        viewModel = ViewModelProviders.of(this).get(CheeseDetailViewModel::class.java)
        viewModel.setCheeseId(cheeseId)
        viewModel.cheese.observe(this, Observer {
            it?.let { cheese ->
                // The name
                name.text = cheese.name
                // Show the image
                image.setImageResource(Cheeses.getDrawableForCheese(cheese.name))
                //
                favorite.isEnabled = true
                if (cheese.favorite) {
                    favorite.setImageResource(R.drawable.ic_favorite)
                    favorite.contentDescription = getString(R.string.cheese_favorite_true)
                } else {
                    favorite.setImageResource(R.drawable.ic_favorite_border)
                    favorite.contentDescription = getString(R.string.cheese_favorite_mark)
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cheese_detail, container, false)
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