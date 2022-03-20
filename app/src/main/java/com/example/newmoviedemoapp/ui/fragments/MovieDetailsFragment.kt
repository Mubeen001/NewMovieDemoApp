package com.example.newmoviedemoapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newmoviedemoapp.R
import com.example.newmoviedemoapp.databinding.FragmentMovieDetailsBinding
import com.example.newmoviedemoapp.model.MovieDeatilsResponse
import com.example.newmoviedemoapp.utils.IMAGE_BASE_URL
import com.example.newmoviedemoapp.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


open class MovieDetailsFragment : Fragment() {

    val args: MovieDetailsFragmentArgs by navArgs()
    val viewModel : MainActivityViewModel by sharedViewModel()
    val binding: FragmentMovieDetailsBinding by lazy {
        FragmentMovieDetailsBinding.inflate(layoutInflater)
    }
    var localResult: MovieDeatilsResponse? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovieDeatild(args.model.id.toString(), "fbedc1ea1afdea3cb9eeef92310dcbe6")

        initObserver()

        Glide.with(binding.image.context)
            .load(IMAGE_BASE_URL + args.model.poster_path).into(binding.image)

        binding.dec.text = args.model.overview

        binding.btnback.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnwatchpromo.setOnClickListener {
            val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToVideoPlayerFragment(localResult?.results?.get(0)?.key!!)
            findNavController().navigate(action)
        }
    }

    fun initObserver(){
        viewModel.movieDetailsResult.observe(viewLifecycleOwner, Observer {
            localResult = it
        })
    }



}