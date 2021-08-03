package com.rumahgugun.pilem.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.rumahgugun.pilem.main.MainActivity
import com.rumahgugun.pilem.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}