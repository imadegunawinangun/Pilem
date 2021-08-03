package com.rumahgugun.pilem.main

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rumahgugun.pilem.R
import com.rumahgugun.pilem.main.movies.MoviesFragment
import com.rumahgugun.pilem.main.tvs.TvsFragment

class MainSectionsPagerAdapter(private val context: Context, supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tvs)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MoviesFragment()
            1 -> TvsFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence = context.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size

}
