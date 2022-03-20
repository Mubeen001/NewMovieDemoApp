package com.example.newmoviedemoapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newmoviedemoapp.databinding.FragmentVideoPlayerBinding
import com.google.android.exoplayer2.Player
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class VideoPlayerFragment : Fragment() , Player.EventListener {

    val args : VideoPlayerFragmentArgs by navArgs()

    val binding : FragmentVideoPlayerBinding by lazy {
        FragmentVideoPlayerBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        getLifecycle().addObserver(binding.youtubePlayerView)
        Log.e("Moviee", "onViewCreated: YOUTUBE VIDEO ID -> ${args.videoId}" )


        binding.btnVideoDone.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = args.videoId.toString()
                youTubePlayer.loadVideo(videoId, 0f)
                binding.progress.visibility = View.GONE
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.youtubePlayerView.release()
    }



    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

}