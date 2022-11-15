package com.example.assignment6.ui.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment6.R
import com.example.assignment6.adapter.MyAdapter
import com.example.assignment6.data.User
import com.example.assignment6.data.Work
import com.example.assignment6.databinding.ActivityMainBinding
import com.example.assignment6.ui.dialog.SettingsDialog
import com.example.assignment6.ui.dialog.WorkDialog
import com.example.assignment6.util.AppUtils
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), WorkDialog.WorkDialogListener,
    SettingsDialog.SettingDialogListener {

    private lateinit var binding: ActivityMainBinding
    private var sharedPref: SharedPreferences = AppUtils.getSharedPref()
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showWorkDialog()

        val theme = AppUtils.getPref(getString(R.string.saved_theme))
        val gson = Gson()
        val data = AppUtils.getPref(getString(R.string.login_user))
        val user = gson.fromJson(data, User::class.java)
        if (theme != null) AppUtils.decideTheme(theme)

        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        TabLayoutMediator(binding.tabLayout, binding.content) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Home"
                    tab.setIcon(R.drawable.icon_home)
                }
                1 -> {
                    tab.text = "About Me"
                    tab.setIcon(R.drawable.icon_about_me)
                }
                2 -> {
                    tab.text = "Work"
                    tab.setIcon(R.drawable.icon_business)
                }
                3 -> {
                    tab.text = "Contact me"
                    tab.setIcon(R.drawable.icon_contacts)
                }
            }
        }.attach()

        user?.apply { binding.toolbar.title = "${user.username}'s CV" }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.cvmenu_setting -> {
                    SettingsDialog().show(supportFragmentManager, SettingsDialog::class.java.name)
                    return@setOnMenuItemClickListener true
                }
                R.id.cvmenu_logout -> {
                    finish()
                    return@setOnMenuItemClickListener true
                }
                else -> false
            }
        }
    }

    override fun onChangeTheme(theme: String) {
        with(sharedPref.edit()) {
            putString(getString(R.string.saved_theme), theme)
            apply()
        }
        AppUtils.decideTheme(theme)
    }

    private fun showWorkDialog() {
        adapter = MyAdapter(supportFragmentManager, lifecycle)
        binding.content.adapter = adapter
    }

    override fun addWork(work: Work) {
        if (!::adapter.isInitialized) {
            showWorkDialog()
        }
        adapter.addWork(work)
    }

}