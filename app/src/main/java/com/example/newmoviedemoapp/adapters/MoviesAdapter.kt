package com.example.newmoviedemoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newmoviedemoapp.R
import com.example.newmoviedemoapp.databinding.MoviesItemsBinding
import com.example.newmoviedemoapp.model.moviesResult
import com.example.newmoviedemoapp.utils.IMAGE_BASE_URL

class MoviesAdapter(var callback : (moviesResult) -> Unit) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var mList = arrayListOf<moviesResult>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bindig = MoviesItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(bindig)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = mList.get(position)
        (holder as MovieViewHolder).bind(model,callback)
    }

    fun upDateList(list: ArrayList<moviesResult>){
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    fun setFilter(filterdNames: ArrayList<moviesResult>){
        this.mList = filterdNames
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mList.size

    class MovieViewHolder(var binding: MoviesItemsBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(model: moviesResult,callback : (moviesResult) -> Unit ){

            Glide.with(binding.thumbnail).load(IMAGE_BASE_URL+model.backdrop_path)
                .error(R.drawable.ic_more)
                .into(binding.thumbnail)

            binding.tvMoviename.text = model.original_title

            binding.parent.setOnClickListener {
                callback.invoke(model)
            }



        }

    }

}