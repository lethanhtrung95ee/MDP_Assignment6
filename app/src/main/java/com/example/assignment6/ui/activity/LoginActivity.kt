package com.example.assignment6.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment6.R
import com.example.assignment6.data.User
import com.example.assignment6.databinding.ActivityLoginBinding
import com.example.assignment6.util.AppUtils
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = AppUtils.setPref(this)
        val gson = Gson()
        val data = AppUtils.getPref(getString(R.string.login_user))
        val user = gson.fromJson(data, User::class.java)
        user?.username?.let { binding.etEmail.setText(user.username) }
        user?.password?.let { binding.etPassword.setText(user.password) }
        val theme = AppUtils.getPref(getString(R.string.saved_theme))
        if (theme != null) AppUtils.decideTheme(theme)
    }

    fun onLogin(view: View) {
        val user = binding.etEmail.text.toString().trim()
        val pass = binding.etPassword.text.toString().trim()

        if (user.isEmpty()) {
            Toast.makeText(applicationContext, "Please enter your username", Toast.LENGTH_LONG)
                .show()
            return
        }
        if (pass.isEmpty()) {
            Toast.makeText(applicationContext, "Please enter your password", Toast.LENGTH_LONG)
                .show()
            return
        }
        openMainActivity(user, pass)
    }

    private fun openMainActivity(user: String, pass: String) {
        with(sharedPref.edit()) {
            val gson = Gson()
            val user = User(user, pass)
            val json = gson.toJson(user)
            putString(getString(R.string.login_user), json)
            apply()
        }

        startActivity(Intent(this, MainActivity::class.java))
    }

}