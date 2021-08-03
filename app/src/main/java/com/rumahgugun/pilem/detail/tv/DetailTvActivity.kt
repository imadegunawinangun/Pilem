package com.rumahgugun.pilem.detail.tv

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rumahgugun.pilem.R
import com.rumahgugun.pilem.core.data.Status
import com.rumahgugun.pilem.core.domain.model.Tv
import com.rumahgugun.pilem.core.utils.Loading
import com.rumahgugun.pilem.core.utils.UrlGambar
import com.rumahgugun.pilem.databinding.ActivityDetailTvBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailTvActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailTvBinding
    private val detailTvViewModel: DetailTvViewModel by viewModel()
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

/*
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
*/

        val extras = intent.extras
        if (extras != null) {
            val itemName = extras.get("extra_name")
            Log.d("DetailTvViewModel", "kudako "+extras)

            detailTvViewModel.setSelectedItem(itemName.toString())

            detailTvViewModel.getTvItem.observe(this, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> Loading().loadingScreenOn(binding.progressBar)
                        Status.SUCCESS -> if (it.data != null) {
                            Loading().loadingScreenOff(binding.progressBar)
                            populateTv(it.data)
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

    private fun populateTv(entity: Tv?) {
        if (entity != null) {
            binding.tvDetailNameTv.text = entity.name
            binding.tvDetailDescriptionTv.text = entity.description
            binding.tvDetailDurationTv.text = entity.duration
            binding.tvDetailRatingTv.text = entity.rating.toString()

            Glide.with(this)
                .load(UrlGambar.link+entity.picture)
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

        detailTvViewModel.getTvItem.observe(this, {
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
            detailTvViewModel.setBookmark()
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
