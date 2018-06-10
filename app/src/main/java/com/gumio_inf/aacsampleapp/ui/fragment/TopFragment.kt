package com.gumio_inf.aacsampleapp.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gumio_inf.aacsampleapp.R
import com.gumio_inf.aacsampleapp.model.User
import com.gumio_inf.aacsampleapp.model.UserViewModel
import kotlinx.android.synthetic.main.fragment_top.*

class TopFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userA = ViewModelProviders.of(this).get(UserViewModel::class.java)
        val userB = ViewModelProviders.of(activity!!).get(UserViewModel::class.java)
        val userC = ViewModelProviders.of(this).get(UserViewModel::class.java)

        var result = userA == userB
        Log.d("TopFragment: A = B", result.toString())

        result = userB == userC

        Log.d("TopFragment: B = C", result.toString())

        result = userC == userA

        Log.d("TopFragment: C = A", result.toString())

        userA.getUser()?.postValue(User("daigo", 19))

        userA.getUser()?.observe(this, Observer {
            text2.text = it?.name
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_top, null)
        view.tag = "TOP"
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button2.setOnClickListener {
            ViewModelProviders.of(activity!!).get(UserViewModel::class.java)
                    .getUser()?.postValue(User("mac", 11))
        }
    }
}