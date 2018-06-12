package com.gumio_inf.aacsampleapp.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.gumio_inf.aacsampleapp.R
import com.gumio_inf.aacsampleapp.ui.fragments.BottomFragment
import com.gumio_inf.aacsampleapp.ui.fragments.TopFragment
import com.gumio_inf.aacsampleapp.ui.viewmodel.User
import com.gumio_inf.aacsampleapp.ui.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_view_model.*



class ViewModelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        val userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.getUser()?.observe(this, Observer {
            textView.text = it?.name
        })

        button.setOnClickListener {
            ViewModelProviders.of(supportFragmentManager.findFragmentByTag("android:switcher:" + R.id.viewPager + ":" + 0) as TopFragment).get(UserViewModel::class.java)
                    .getUser()?.postValue(User("mojave", 222))
            ViewModelProviders.of(supportFragmentManager.findFragmentByTag("android:switcher:" + R.id.viewPager + ":" + 1) as BottomFragment).get(UserViewModel::class.java)
                    .getUser()?.postValue(User("mojavgaaaaa", 222))
            textView2.text = "applyed!!"
        }

        val manager = supportFragmentManager

        val viewPager = viewPager
        val adapter = ExampleFragmentPagerAdapter(manager)
        viewPager.adapter = adapter
        val tabLayout = tabLayout
        tabLayout.setupWithViewPager(viewPager)
    }
}

class ExampleFragmentPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return TopFragment()
            1 -> return BottomFragment()
        }
        return null
    }

    override fun getCount(): Int  = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return "ページ" + (position + 1)
    }
}
