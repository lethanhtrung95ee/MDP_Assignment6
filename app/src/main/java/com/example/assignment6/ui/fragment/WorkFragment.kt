package com.example.assignment6.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment6.R
import com.example.assignment6.adapter.WorkAdapter
import com.example.assignment6.data.Work
import com.example.assignment6.ui.dialog.WorkDialog

class WorkFragment : Fragment(R.layout.fragment_work) {

    private lateinit var adapter: WorkAdapter
    private var workList = mutableListOf<Work>()
    private lateinit var recyclerView: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView = view.findViewById<View>(R.id.recycler_view) as RecyclerView

        if (context != null) {
            workList = mutableListOf(
                Work(
                    getString(R.string.momo_inc),
                    getString(R.string.momo_title),
                    getString(R.string._2021_2021),
                    R.drawable.momo
                ),
                Work(
                    getString(R.string.unit_inc),
                    getString(R.string.unit_tile),
                    getString(R.string._2020_2021),
                    R.drawable.unit
                ),
                Work(
                    getString(R.string.bosch_inc),
                    getString(R.string.bosch_title),
                    getString(R.string._2019_2020),
                    R.drawable.bosch
                ),
            )
            setupRecyclerView()
        }

        view.findViewById<View>(R.id.fab).setOnClickListener {
            WorkDialog().show(
                parentFragmentManager,
                WorkDialog::class.java.name
            )
        }
    }

    private fun setupRecyclerView() {
        if (::recyclerView.isInitialized) {
            adapter = WorkAdapter(workList)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onAddWOrk(work: Work) {
        workList.add(work)
        if (::adapter.isInitialized) {
            adapter.notifyDataSetChanged()
        } else {
            setupRecyclerView()
        }
    }
}