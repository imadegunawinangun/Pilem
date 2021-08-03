package com.rumahgugun.pilem.favorite.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rumahgugun.pilem.favorite.FavoriteViewModel
import com.rumahgugun.pilem.core.ui.MoviesAdapter
import com.rumahgugun.pilem.databinding.MoviesFragmentBinding
import com.rumahgugun.pilem.core.utils.Loading
import com.rumahgugun.pilem.detail.movie.DetailMovieActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMoviesFragment : Fragment() {

    private var _binding: MoviesFragmentBinding? = null
    private val binding get() = _binding
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = MoviesFragmentBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Loading().loadingScreenOn(binding?.progressBar)



        if (activity != null) {
            val moviesAdapter = MoviesAdapter()
            moviesAdapter.onItemClick = {
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_NAME, it.id.toString())
                startActivity(intent)
            }
            favoriteViewModel.getMovies().observe(viewLifecycleOwner, {
                moviesAdapter.setItems(it)
                moviesAdapter.notifyDataSetChanged()
                Loading().loadingScreenOff(binding?.progressBar)

            })

            binding?.recyclerViewMovies?.setHasFixedSize(true)
            binding?.recyclerViewMovies?.layoutManager = LinearLayoutManager(activity)
            binding?.recyclerViewMovies?.adapter = moviesAdapter

        }
    }
}