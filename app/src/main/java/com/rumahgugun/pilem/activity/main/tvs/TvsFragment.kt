package com.rumahgugun.pilem.activity.main.tvs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rumahgugun.pilem.activity.main.MainViewModel
import com.rumahgugun.pilem.databinding.TvsFragmentBinding
import com.rumahgugun.pilem.utils.Loading
import com.rumahgugun.pilem.viewmodel.ViewModelFactory

class TvsFragment : Fragment() {

    private var _binding: TvsFragmentBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = TvsFragmentBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Loading().loadingScreenOn(binding?.progressBar)
        val factory = ViewModelFactory.getInstance(requireActivity())

        val viewModel = ViewModelProvider(this,factory)[MainViewModel::class.java]

        if (activity != null) {
            val tvsAdapter = TvsAdapter()
            viewModel.getTvs().observe(viewLifecycleOwner, {
                tvsAdapter.setItems(it.data)
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