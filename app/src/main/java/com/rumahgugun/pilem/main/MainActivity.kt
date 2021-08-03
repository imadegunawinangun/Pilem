package com.rumahgugun.pilem.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.rumahgugun.pilem.R
import com.rumahgugun.pilem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.elevation = 0f

        val sectionPagerAdapter = MainSectionsPagerAdapter(this, supportFragmentManager)
        binding?.apply {
            viewPager.adapter = sectionPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        setBookmarkState(false)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("pilem://favorite"))
            startActivity(intent)
            Toast.makeText(
                applicationContext,
                "Menampilkan daftar favoritku",
                Toast.LENGTH_SHORT
            ).show()
            return true
        }
        if (item.itemId == R.id.profil) {
            val uri = Uri.parse("pilem://profil")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
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