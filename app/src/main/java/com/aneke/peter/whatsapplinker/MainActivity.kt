package com.aneke.peter.whatsapplinker

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.aneke.peter.whatsapplinker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel


        val number = intent.dataString?.replace("tel:", "")?.trim()
        if (!number.isNullOrBlank()) {
            mainViewModel.phoneNumber.value = number
        }

        binding.linkText.setOnClickListener {
            val defaultBrowser = Intent(Intent.ACTION_VIEW)
            defaultBrowser.data = Uri.parse(mainViewModel.link.value)
            startActivity(defaultBrowser)
        }

        mainViewModel.link.observe(this) {
            if (it.isNullOrBlank()){
                binding.linkText.visibility = View.GONE
            } else {
                binding.linkText.visibility = View.VISIBLE
            }
        }
    }


}