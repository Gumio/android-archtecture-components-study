package com.gumio_inf.aacsampleapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gumio_inf.aacsampleapp.R
import com.gumio_inf.aacsampleapp.ui.fragments.CheeseDetailFragment
import com.gumio_inf.aacsampleapp.ui.fragments.CheeseListFragment
import kotlinx.android.synthetic.main.activity_main.*

class RoomActivity : AppCompatActivity(), CheeseListFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, CheeseListFragment.newInstance())
                    .commitNow()
        }
    }

    override fun onCheeseSelected(cheeseId: Long) {
        supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, CheeseDetailFragment.newInstance(cheeseId))
                .commit()
    }
}
