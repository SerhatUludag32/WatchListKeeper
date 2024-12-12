package com.serhatuludag.watchlistkeeper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.serhatuludag.watchlistkeeper.R
import com.serhatuludag.watchlistkeeper.model.Movie
import javax.inject.Inject

class MovieRecyclerAdapter @Inject constructor(val glide: RequestManager) : RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<Movie>(){

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)

    var movies : List<Movie>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_row,parent,false)
        return MovieViewHolder(view)
    }


    override fun getItemCount(): Int {
        return movies.size
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.movieRowImageView)
        val nameText = holder.itemView.findViewById<TextView>(R.id.movieRowNameText)
        val directorText = holder.itemView.findViewById<TextView>(R.id.movieRowDirectorText)
        val releaseYear = holder.itemView.findViewById<TextView>(R.id.movieRowYearText)
        val movie = movies[position]
        holder.itemView.apply {
            nameText.text = "Name: ${movie.movieName}"
            directorText.text = "Director: ${movie.directorName}"
            releaseYear.text = "Year: ${movie.releaseYear}"
            glide.load(movie.imageUrl).into(imageView)

        }

    }
}