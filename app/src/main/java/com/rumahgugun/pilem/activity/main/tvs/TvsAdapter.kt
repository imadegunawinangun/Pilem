package com.rumahgugun.pilem.activity.main.tvs

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rumahgugun.pilem.R
import com.rumahgugun.pilem.activity.detail.tv.DetailTvActivity
import com.rumahgugun.pilem.data.source.local.entity.TvEntity
import com.rumahgugun.pilem.databinding.ItemCardBinding


class TvsAdapter : RecyclerView.Adapter<TvsAdapter.TvsViewHolder>() {
    private var listTvs = ArrayList<TvEntity>()

    fun setItems(items: List<TvEntity>?) {
        if (items == null) return
        this.listTvs.clear()
        this.listTvs.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvsViewHolder {
        val itemBinding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TvsViewHolder, position: Int) {
        val course = listTvs[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listTvs.size


    class TvsViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: TvEntity) {
            with(binding) {
                tvItemName.text = entity.name
                tvItemDescription.text = entity.description
                tvItemRating.text = entity.rating
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvActivity::class.java)
                    intent.putExtra(DetailTvActivity.EXTRA_NAME, entity.name)
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