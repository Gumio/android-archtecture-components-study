package com.gumio_inf.aacsampleapp.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.transition.Slide
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gumio_inf.aacsampleapp.R
import com.gumio_inf.aacsampleapp.ui.adapter.RamenListAdapter
import com.gumio_inf.aacsampleapp.ui.viewmodel.RamenListViewModel

class RamenListFragment : Fragment() {

    private lateinit var adapter: RamenListAdapter

    interface Listener {
        fun onRamenSelected(ramenId: Long)
    }

    companion object {
        fun newInstance() = RamenListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = Slide(GravityCompat.START).apply { duration = 150 }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(RamenListViewModel::class.java)
        viewModel.ramens.observe(this, Observer { adapter.setRamens(it) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ramen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<RecyclerView>(R.id.list).run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = RamenListAdapter(view.context, { ramenId ->
                activity?.let {
                    if (!it.isFinishing) {
                        (it as Listener).onRamenSelected(ramenId)
                    }
                }
            }).also {
                this@RamenListFragment.adapter = it
            }
        }
    }

}

