package com.rumahgugun.pilem.activity.detail.tv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rumahgugun.pilem.R
import com.rumahgugun.pilem.activity.detail.movie.DetailMovieViewModel
import com.rumahgugun.pilem.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.data.source.local.entity.TvEntity
import com.rumahgugun.pilem.databinding.ActivityDetailTvBinding
import com.rumahgugun.pilem.utils.Loading
import com.rumahgugun.pilem.viewmodel.ViewModelFactory
import com.rumahgugun.pilem.vo.Status

class DetailTvActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailTvBinding
    private lateinit var viewModel: DetailTvViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailTvViewModel::class.java]

        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(binding?.root)

/*
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
*/

        val extras = intent.extras
        if (extras != null) {
            val itemName = extras.getString(EXTRA_NAME)
            viewModel.setSelectedItem(itemName.toString())

            viewModel.getTvItem.observe(this, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> Loading().loadingScreenOn(binding?.progressBar)
                        Status.SUCCESS -> if (it.data != null) {
                            Loading().loadingScreenOff(binding?.progressBar)
                            populateTv(it.data)
                        }
                        Status.ERROR -> {
                            Loading().loadingScreenOff(binding?.progressBar)
                            Toast.makeText(
                                applicationContext,
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })

        }
    }

    private fun populateTv(entity: TvEntity?) {
        if (entity != null) {
            binding.tvDetailNameTv.text = entity.name
            binding.tvDetailDescriptionTv.text = entity.description
            binding.tvDetailDurationTv.text = entity.duration
            binding.tvDetailRatingTv.text = entity.rating

            Glide.with(this)
                .load(entity.picture)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.imageViewDetailTv)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu

        viewModel.getTvItem.observe(this, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> Loading().loadingScreenOn(binding?.progressBar)
                    Status.SUCCESS -> if (it.data != null) {
                        Loading().loadingScreenOff(binding?.progressBar)
                        val state = it.data.isFavorite
                        setBookmarkState(state)
                    }
                    Status.ERROR -> {
                        Loading().loadingScreenOff(binding?.progressBar)
                        Toast.makeText(
                            applicationContext,
                            "Terjadi kesalahan",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            viewModel.setBookmark()
            Toast.makeText(
                applicationContext,
                "Brehasil",
                Toast.LENGTH_SHORT
            ).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBookmarkState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_bookmark_24)
        } else {
            menuItem?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_baseline_bookmark_border_24)
        }
    }
    companion object {
        const val EXTRA_NAME = "extra_name"
    }
}
