package com.example.newmoviedemoapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.adapters.MainAdapter
import com.example.myapplication.ui.fragments.MainFragmentFactory
import com.example.newmoviedemoapp.R
import com.example.newmoviedemoapp.databinding.ActivityMainBinding
import com.example.newmoviedemoapp.viewmodel.MainActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel : MainActivityViewModel by viewModel()
    val mainDatabinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainDatabinding.root)

    }



}