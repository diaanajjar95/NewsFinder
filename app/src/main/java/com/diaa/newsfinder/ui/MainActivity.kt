package com.diaa.newsfinder.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.diaa.newsfinder.AppListener
import com.diaa.newsfinder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AppListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun showLoader() {
        binding.layoutLoading.root.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        binding.layoutLoading.root.visibility = View.GONE
    }

    override fun showMessage(message: String?) {
        message?.let {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}