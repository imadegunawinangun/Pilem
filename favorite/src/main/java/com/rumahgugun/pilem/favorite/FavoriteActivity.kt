package com.rumahgugun.pilem.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.rumahgugun.pilem.R
import com.rumahgugun.pilem.main.MainActivity
import com.rumahgugun.pilem.databinding.ActivityMainBinding
import com.rumahgugun.pilem.profil.di.favoriteModule
import org.koin.core.context.loadKoinModules

class FavoriteActivity  : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.title = "Favoritku"
        loadKoinModules(favoriteModule)
        val sectionPagerAdapter = FavoriteSectionsPagerAdapter(this, supportFragmentManager)
        binding?.apply {
            viewPager.adapter = sectionPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        setBookmarkState(true)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(
                applicationContext,
                "Kembali ke Beranda",
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


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}