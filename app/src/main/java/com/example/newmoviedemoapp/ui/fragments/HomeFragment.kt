package com.example.newmoviedemoapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.adapters.MainAdapter
import com.example.myapplication.ui.fragments.MainFragmentFactory
import com.example.newmoviedemoapp.R
import com.example.newmoviedemoapp.databinding.FragmentHomeBinding
import com.example.newmoviedemoapp.viewmodel.MainActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment : Fragment() {

    val mainDatabinding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    lateinit var mainAdapter: MainAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return mainDatabinding.root
    }
    val viewModel: MainActivityViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        setUpBottomNavigation()
        getMovieData()
    }

    private fun getMovieData() {
        viewModel.getAllMovies()
    }

    fun setUpViewPager() {
        val fragmentFactory = MainFragmentFactory()
        mainAdapter = MainAdapter(childFragmentManager, fragmentFactory)
        mainDatabinding.viewPager.adapter = mainAdapter
        mainDatabinding.viewPager.addOnPageChangeListener(onPageChangeListener)
        mainDatabinding.viewPager.currentItem = 1
    }

    fun setUpBottomNavigation() {
        mainDatabinding.bottomnav.setOnNavigationItemSelectedListener(itemSelectedListener)
    }


    private val itemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.tab_watch -> {
                    mainDatabinding.viewPager.currentItem = 1
                }
                else -> mainDatabinding.viewPager.currentItem = 1
            }
            true
        }


    private val onPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {

        }

        override fun onPageSelected(position: Int) {
            mainDatabinding.bottomnav!!.getMenu().getItem(position).setChecked(true)
        }
        override fun onPageScrollStateChanged(state: Int) {

        }
    }


}