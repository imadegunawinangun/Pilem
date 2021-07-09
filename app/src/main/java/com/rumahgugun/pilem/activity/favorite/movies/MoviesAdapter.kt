package com.rumahgugun.pilem.activity.favorite.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rumahgugun.pilem.R
import com.rumahgugun.pilem.activity.detail.movie.DetailMovieActivity
import com.rumahgugun.pilem.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.databinding.ItemCardBinding
import java.util.ArrayList

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private var listMovie = ArrayList<MovieEntity>()

    fun setItems(items: List<MovieEntity>?) {
        if (items == null) return
        this.listMovie.clear()
        this.listMovie.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemBinding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val course = listMovie[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listMovie.size


    class MoviesViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: MovieEntity) {
            with(binding) {
                tvItemName.text = entity.name
                tvItemDescription.text = entity.description
                tvItemRating.text = entity.rating
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_NAME, entity.name)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(entity.picture)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgItem)
            }
        }
    }
}