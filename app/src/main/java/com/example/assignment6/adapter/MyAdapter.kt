package com.example.assignment6.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.assignment6.data.Work
import com.example.assignment6.ui.fragment.AboutMeFragment
import com.example.assignment6.ui.fragment.ContactMeFragment
import com.example.assignment6.ui.fragment.HomeFragment
import com.example.assignment6.ui.fragment.WorkFragment

class MyAdapter(fm: FragmentManager, lc: Lifecycle) : FragmentStateAdapter(fm, lc) {
    override fun getItemCount(): Int {
        return 4
    }
    val workFragment = WorkFragment()
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> AboutMeFragment()
            2 -> workFragment
            3 -> ContactMeFragment()
            else -> Fragment()
        }
    }

    fun addWork(work: Work) {
        workFragment.onAddWOrk(work)
    }
}