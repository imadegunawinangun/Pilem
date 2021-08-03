package com.rumahgugun.pilem.favorite.tvs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rumahgugun.pilem.core.ui.TvsAdapter
import com.rumahgugun.pilem.core.utils.Loading
import com.rumahgugun.pilem.databinding.TvsFragmentBinding
import com.rumahgugun.pilem.detail.tv.DetailTvActivity
import com.rumahgugun.pilem.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteTvsFragment : Fragment() {

    private var _binding: TvsFragmentBinding? = null
    private val binding get() = _binding
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = TvsFragmentBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Loading().loadingScreenOn(binding?.progressBar)

        if (activity != null) {
            val tvsAdapter = TvsAdapter()
            tvsAdapter.onItemClick = {
                val intent = Intent(activity, DetailTvActivity::class.java)
                intent.putExtra(DetailTvActivity.EXTRA_NAME, it.id.toString())
                startActivity(intent)
            }
            favoriteViewModel.getTvs().observe(viewLifecycleOwner, {
                tvsAdapter.setItems(it)
                tvsAdapter.notifyDataSetChanged()
                Loading().loadingScreenOff(binding?.progressBar)
            })

            binding?.recyclerViewTvs?.setHasFixedSize(true)
            binding?.recyclerViewTvs?.layoutManager = LinearLayoutManager(activity)
            binding?.recyclerViewTvs?.adapter = tvsAdapter

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}