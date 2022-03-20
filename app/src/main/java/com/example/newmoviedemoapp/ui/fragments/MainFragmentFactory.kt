package com.example.myapplication.ui.fragments

import androidx.fragment.app.Fragment


interface FragmentFactory{
    fun createWatchFragment() :Fragment
}

class MainFragmentFactory : FragmentFactory {

    override fun createWatchFragment() = WatchFragment.newInstance()


}