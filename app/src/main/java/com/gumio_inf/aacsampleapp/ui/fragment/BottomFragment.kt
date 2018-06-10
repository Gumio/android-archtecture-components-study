package com.gumio_inf.aacsampleapp.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gumio_inf.aacsampleapp.R
import com.gumio_inf.aacsampleapp.model.User
import com.gumio_inf.aacsampleapp.model.UserViewModel
import kotlinx.android.synthetic.main.fragment_bottom.*


class BottomFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = ViewModelProviders.of(this).get(UserViewModel::class.java)

        user.getUser()?.postValue(User("arashi", 22))
        user.getUser()?.observe(this, Observer {
            text3.text = it?.name
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bottom, null)
        view.tag = "BOTTOM"
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button3.setOnClickListener {
            ViewModelProviders.of(activity!!).get(UserViewModel::class.java)
                    .getUser()?.postValue(User("hitsuzi", 4))
        }
    }
}
