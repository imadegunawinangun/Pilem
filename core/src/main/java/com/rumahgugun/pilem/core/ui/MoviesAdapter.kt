package com.rumahgugun.pilem.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rumahgugun.pilem.core.R
import com.rumahgugun.pilem.core.databinding.ItemCardBinding
import com.rumahgugun.pilem.core.domain.model.Movie
import com.rumahgugun.pilem.core.utils.UrlGambar


class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private var listMovie = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setItems(items: List<Movie>?) {
        if (items == null) return
        this.listMovie.clear()
        this.listMovie.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return         MoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false))

    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val course = listMovie[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listMovie.size


    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCardBinding.bind(itemView)
        fun bind(entity: Movie) {
            with(binding) {
                tvItemName.text = entity.name
                tvItemDescription.text = entity.description
                tvItemRating.text = entity.rating.toString()

                Glide.with(itemView.context)
                    .load(UrlGambar.link+entity.picture)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgItem)
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listMovie[adapterPosition])
            }
        }
    }
}