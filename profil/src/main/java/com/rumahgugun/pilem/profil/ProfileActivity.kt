package com.rumahgugun.pilem.profil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rumahgugun.pilem.core.data.Resource
import com.rumahgugun.pilem.profil.databinding.ActivityProfileBinding
import com.rumahgugun.pilem.profil.di.profilModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.loadKoinModules

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val profileViewModel: ProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(profilModule)


        supportActionBar?.title = "Profil Developer"
        getProfile()
    }

    private fun getProfile() {
        val it = profileViewModel.profil
        binding.progressBar.visibility = View.GONE
        binding.tvMaps.text = "${it.nama} | ${it.status} | ${it.email}"
    }


}