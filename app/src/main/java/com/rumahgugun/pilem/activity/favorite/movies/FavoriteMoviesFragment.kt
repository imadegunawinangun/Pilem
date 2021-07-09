package com.rumahgugun.pilem.activity.favorite.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rumahgugun.pilem.activity.favorite.FavoriteViewModel
import com.rumahgugun.pilem.databinding.MoviesFragmentBinding
import com.rumahgugun.pilem.utils.Loading
import com.rumahgugun.pilem.viewmodel.ViewModelFactory

class FavoriteMoviesFragment : Fragment() {

    private var _binding: MoviesFragmentBinding? = null
    private val binding get() = _binding

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
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
            val moviesAdapter = MoviesAdapter()
            viewModel.getMovies().observe(this, {
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