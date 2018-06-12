package com.gumio_inf.aacsampleapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.View
import com.gumio_inf.aacsampleapp.ui.activities.LifecyclesActivity
import com.gumio_inf.aacsampleapp.ui.activities.LiveDataActivity
import com.gumio_inf.aacsampleapp.ui.activities.RoomActivity
import com.gumio_inf.aacsampleapp.ui.activities.ViewModelActivity
import com.gumio_inf.aacsampleapp.ui.adapter.RecyclerAdapter
import com.gumio_inf.aacsampleapp.ui.adapter.RecyclerViewHolder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), RecyclerViewHolder.ItemClickListener {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        findViewById<View>(android.R.id.content).systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        val lists = resources.getStringArray(R.array.aac_name).toMutableList()
        recyclerView?.adapter = RecyclerAdapter(this, this, lists)
    }

    override fun onItemClick(view: View, position: Int) {
        when(position) {
            0 -> startActivity(Intent(this, LifecyclesActivity::class.java))
            1 -> startActivity(Intent(this, LiveDataActivity::class.java))
            2 -> startActivity(Intent(this, ViewModelActivity::class.java))
            3 -> startActivity(Intent(this, RoomActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options, menu)
        return true
    }
}


