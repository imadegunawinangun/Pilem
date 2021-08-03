package com.rumahgugun.pilem.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rumahgugun.pilem.core.R
import com.rumahgugun.pilem.core.databinding.ItemCardBinding
import com.rumahgugun.pilem.core.domain.model.Tv
import com.rumahgugun.pilem.core.utils.UrlGambar


class TvsAdapter : RecyclerView.Adapter<TvsAdapter.TvsViewHolder>() {
    private var listTvs = ArrayList<Tv>()
    var onItemClick: ((Tv) -> Unit)? = null

    fun setItems(items: List<Tv>?) {
        if (items == null) return
        listTvs.clear()
        listTvs.addAll(items)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvsViewHolder {
        return TvsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false))

    }

    override fun onBindViewHolder(holder: TvsViewHolder, position: Int) {
        val course = listTvs[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listTvs.size


    inner class TvsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCardBinding.bind(itemView)
        fun bind(entity: Tv) {
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
                onItemClick?.invoke(listTvs[adapterPosition])
            }
        }
    }
}