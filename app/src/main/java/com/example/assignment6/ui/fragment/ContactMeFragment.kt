package com.example.assignment6.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.assignment6.R
import com.example.assignment6.ui.activity.WebActivity

class ContactMeFragment : Fragment(R.layout.fragment_contact_me) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<LinearLayout>(R.id.phone).setOnClickListener { onPhoneClicked() }
        view.findViewById<LinearLayout>(R.id.email).setOnClickListener { onEmailClicked() }
        view.findViewById<LinearLayout>(R.id.linkedIn).setOnClickListener { onLinkedInClicked() }
        view.findViewById<LinearLayout>(R.id.github).setOnClickListener { onGithubClicked() }
    }

    private fun onPhoneClicked() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse(getString(R.string.phone_number))
        startActivity(intent)
    }

    private fun onEmailClicked() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse(getString(R.string.intent_filter))
        intent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.email))
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body))
        startActivity(intent)
    }

    private fun onLinkedInClicked() {
        val intent = Intent(context, WebActivity::class.java)
        intent.putExtra(getString(R.string.web), getString(R.string.linkedIn))
        startActivity(intent)
    }

    private fun onGithubClicked() {
        val intent = Intent(context, WebActivity::class.java)
        intent.putExtra(getString(R.string.web), getString(R.string.github))
        startActivity(intent)
    }
}