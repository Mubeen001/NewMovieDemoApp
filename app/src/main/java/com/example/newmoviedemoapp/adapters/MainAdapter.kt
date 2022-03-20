package com.example.myapplication.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.ui.fragments.MainFragmentFactory

class MainAdapter constructor(fm: FragmentManager, val mainFragmentFactory: MainFragmentFactory) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> mainFragmentFactory.createWatchFragment()
            else -> mainFragmentFactory.createWatchFragment()
        }
    }
}