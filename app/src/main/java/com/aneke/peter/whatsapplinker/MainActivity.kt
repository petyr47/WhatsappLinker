package com.aneke.peter.whatsapplinker

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.aneke.peter.whatsapplinker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var clipboardManager: ClipboardManager
    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel

        clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

        val number = intent.dataString?.replace("tel:", "")?.trim()
        if (!number.isNullOrBlank()) {
            mainViewModel.phoneNumber.value = number
        }

        binding.linkText.setOnClickListener {
            val defaultBrowser = Intent(Intent.ACTION_VIEW)
            defaultBrowser.data = Uri.parse(mainViewModel.link)
            startActivity(defaultBrowser)
        }

        binding.ccp.registerCarrierNumberEditText(binding.textInput)

        binding.ccp.setPhoneNumberValidityChangeListener { isValid ->
            if (isValid) {
                binding.linkText.visibility = View.VISIBLE
                binding.copyLinkBtn.visibility = View.VISIBLE
                mainViewModel.phoneNumber.value = binding.ccp.fullNumber
                mainViewModel.link = makeWhatsappUrl(binding.ccp.fullNumber)
            } else {
                binding.linkText.visibility = View.GONE
                binding.copyLinkBtn.visibility = View.GONE
            }
        }
        binding.copyLinkBtn.setOnClickListener { copyContent() }
    }

    private fun copyContent() {
        val linkClip = ClipData.newPlainText("link", mainViewModel.link)
        clipboardManager.setPrimaryClip(linkClip)
        Toast.makeText(this, "Link Copied!", Toast.LENGTH_LONG).show()
    }
}
