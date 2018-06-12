package com.gumio_inf.aacsampleapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gumio_inf.aacsampleapp.R
import com.gumio_inf.aacsampleapp.ui.fragments.RamenDetailFragment
import com.gumio_inf.aacsampleapp.ui.fragments.RamenListFragment
import kotlinx.android.synthetic.main.activity_main.*

class RoomActivity : AppCompatActivity(), RamenListFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, RamenListFragment.newInstance())
                    .commitNow()
        }
    }

    override fun onRamenSelected(ramenId: Long) {
        supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, RamenDetailFragment.newInstance(ramenId))
                .commit()
    }
}
