package com.rumahgugun.pilem.detail.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rumahgugun.pilem.R
import com.rumahgugun.pilem.core.data.Status
import com.rumahgugun.pilem.core.domain.model.Movie
import com.rumahgugun.pilem.core.utils.Loading
import com.rumahgugun.pilem.core.utils.UrlGambar
import com.rumahgugun.pilem.databinding.ActivityDetailMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailMovieActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailMovieBinding
    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

/*
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
*/

        val extras = intent.extras
        if (extras != null) {
            val itemName = extras.get("extra_name")
            detailMovieViewModel.setSelectedItem(itemName.toString())

            detailMovieViewModel.getMovieItem.observe(this, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> Loading().loadingScreenOn(binding.progressBar)
                        Status.SUCCESS -> if (it.data != null) {
                            Loading().loadingScreenOff(binding.progressBar)
                            populateMovie(it.data)
                        }
                        Status.ERROR -> {
                            Loading().loadingScreenOff(binding.progressBar)
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

    private fun populateMovie(entity: Movie?) {
        if (entity != null) {
            binding.tvDetailNameMovie.text = entity.name
            binding.tvDetailDescriptionMovie.text = entity.description
            binding.tvDetailDurationMovie.text = entity.duration
            binding.tvDetailRatingMovie.text = entity.rating.toString()

            Glide.with(this)
                .load(UrlGambar.link+entity.picture)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.imageViewDetailMovie)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        detailMovieViewModel.getMovieItem.observe(this, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> Loading().loadingScreenOn(binding.progressBar)
                    Status.SUCCESS -> if (it.data != null) {
                        Loading().loadingScreenOff(binding.progressBar)
                        val state = it.data!!.isFavorite
                        setBookmarkState(state)
                    }
                    Status.ERROR -> {
                        Loading().loadingScreenOff(binding.progressBar)
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
            detailMovieViewModel.setBookmark()
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
