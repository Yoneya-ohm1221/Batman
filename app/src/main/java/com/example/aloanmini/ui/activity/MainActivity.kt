package com.example.aloanmini.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aloanmini.R
import com.example.aloanmini.databinding.ActivityMainBinding
import com.example.aloanmini.ui.fragment.myAccount.UserViewModel
import com.example.aloanmini.ui.utill.Resource
import com.example.aloanmini.ui.utill.Utils


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}