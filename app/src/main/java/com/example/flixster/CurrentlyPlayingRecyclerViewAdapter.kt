package com.example.flixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixster.R.id


/**
 * [RecyclerView.Adapter] that can display a [CurrentlyPlaying] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class CurrentlyPlayingRecyclerViewAdapter(
    private val movies: List<CurrentlyPlaying>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<CurrentlyPlayingRecyclerViewAdapter.MoviesViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_currently_playing, parent, false)
        return MoviesViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class MoviesViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: CurrentlyPlaying? = null
        val mMovieTitle: TextView = mView.findViewById<View>(id.movie_title) as TextView
        val mMovieDescription: TextView = mView.findViewById<View>(id.movie_description) as TextView
        val mMovieImage: ImageView = mView.findViewById<View>(id.movie_image) as ImageView



        override fun toString(): String {
            return mMovieTitle.toString() + " '" + mMovieDescription.text + "'"
        }
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
        val baseurl = "https://image.tmdb.org/t/p/w500/"

        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mMovieDescription.text = movie.author


        Glide.with(holder.mView)

            .load(baseurl + movie.movieImageUrl)
            .centerInside()
            .into(holder.mMovieImage)


        holder.mView.setOnClickListener {
            holder.mItem?.let { movie ->
                mListener?.onItemClick(movie)
            }
        }
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return movies.size
    }
}