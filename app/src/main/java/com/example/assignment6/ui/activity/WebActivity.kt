package com.example.assignment6.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment6.R
import com.example.assignment6.databinding.ActivityWebBinding


class WebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url: String = intent.getSerializableExtra(getString(R.string.web)) as String
        val title: String = when (url) {
            getString(R.string.linkedIn) -> getString(R.string.linkedin_label)
            getString(R.string.github) -> getString(R.string.github_label)
            else -> getString(R.string.app_name)
        }

        binding.toolbar.title = title

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
        }
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.loadUrl(url)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}