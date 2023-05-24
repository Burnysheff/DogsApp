package com.example.dogsapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.webkit.WebChromeClient
import android.webkit.WebSettings.PluginState
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dogsapp.databinding.ContentBinding
import com.example.dogsapp.ui.text.TextFragment


class ContentActivity: AppCompatActivity() {
    private lateinit var binding: ContentBinding
    private lateinit var webView: WebView

    private lateinit var text: TextView
    private lateinit var video: TextView

    val fragment = TextFragment()

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webView = binding.webView
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.pluginState = PluginState.ON
        webView.settings.mediaPlaybackRequiresUserGesture = false
        webView.webChromeClient = WebChromeClient()
        webView.loadUrl("https://youtu.be/cR62InyqRB0")

        text = binding.text
        video = binding.video

        supportFragmentManager.beginTransaction().add(R.id.content, fragment).commit()
        text.textSize = 18F

        val authorView = text
        authorView.movementMethod = LinkMovementMethod.getInstance()
        authorView.setOnClickListener {
            text()
        }
        val linkTextView = video
        linkTextView.movementMethod = LinkMovementMethod.getInstance()
        linkTextView.setOnClickListener {
            video()
        }

        supportActionBar?.hide()
    }

    private fun text() {
        text.textSize = 18F
        video.textSize = 16F

        supportFragmentManager.beginTransaction().add(R.id.content, fragment).commit()
    }
    private fun video() {
        video.textSize = 18F
        text.textSize = 16F

        supportFragmentManager.beginTransaction().remove(fragment).commit()
    }
}