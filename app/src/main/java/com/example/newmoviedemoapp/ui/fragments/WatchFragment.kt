package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.newmoviedemoapp.R
import com.example.newmoviedemoapp.adapters.MoviesAdapter
import com.example.newmoviedemoapp.databinding.FragmentWatchBinding
import com.example.newmoviedemoapp.interfaces.onTextChanged
import com.example.newmoviedemoapp.model.moviesResult
import com.example.newmoviedemoapp.ui.fragments.HomeFragmentDirections
import com.example.newmoviedemoapp.ui.fragments.MovieDetailsFragment
import com.example.newmoviedemoapp.utils.MyTextChangeListener
import com.example.newmoviedemoapp.utils.hideKeybaord
import com.example.newmoviedemoapp.utils.showKeyboard
import com.example.newmoviedemoapp.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class WatchFragment : Fragment() , onTextChanged {

    val binding: FragmentWatchBinding by lazy {
        FragmentWatchBinding.inflate(layoutInflater)
    }

    val viewModel: MainActivityViewModel by sharedViewModel()
    var moviesAdapter: MoviesAdapter? = null
    private var mList= ArrayList<moviesResult>()

    companion object {
        fun newInstance() = WatchFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        clickListenr()
        binding.edsearch?.addTextChangedListener(MyTextChangeListener(binding.edsearch, this))
    }

    fun clickListenr() {

        binding.searchbtn.setOnClickListener {
            binding.searchlayout.visibility = View.VISIBLE
            binding.toolbarwatch.visibility = View.GONE
            binding.searchbtn.context.showKeyboard(binding.edsearch, binding.edsearch)
        }

        binding.btncross.setOnClickListener {
            binding.searchlayout.visibility = View.GONE
            binding.toolbarwatch.visibility = View.VISIBLE
            binding.btncross.context.hideKeybaord(binding.btncross)
        }

    }


    fun initObserver() {
        viewModel.movieList.observe(viewLifecycleOwner, {
            if (it.results.size > 0) {
                binding.rvMovies.visibility = View.VISIBLE
                binding.Progress.visibility = View.GONE
                setUpRecyclerView(it.results as ArrayList)
                mList =  it.results
            } else {
                binding.rvMovies.visibility = View.GONE
                binding.Progress.visibility = View.VISIBLE
            }
        })
    }

    fun setUpRecyclerView(list: ArrayList<moviesResult>) {
        binding.rvMovies?.setHasFixedSize(true)
        binding.rvMovies?.setLayoutManager(
            StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
        )
        moviesAdapter = MoviesAdapter { model ->
            val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(model, "")
            findNavController().navigate(action)

        }
        moviesAdapter?.upDateList(list)
        binding.rvMovies.adapter = moviesAdapter

    }

    override fun onTextChange(editable: Editable) {
        filterQuery(editable.toString())
    }
    fun filterQuery(text: String?) {
        var filterdNames = ArrayList<moviesResult>()
        for (item in this.mList) {
            if (item.title.toLowerCase().contains(text!!)) {
                filterdNames.add(item)
            }
        }
        this.moviesAdapter?.setFilter(filterdNames)
    }

}