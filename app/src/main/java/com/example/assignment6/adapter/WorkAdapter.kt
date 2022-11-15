package com.example.assignment6.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment6.R
import com.example.assignment6.data.Work

class WorkAdapter(private val workList: MutableList<Work>) :
    RecyclerView.Adapter<WorkAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val logo: ImageView? = view.findViewById(R.id.rv_work_logo)
        val title: TextView? = view.findViewById(R.id.rv_work_title)
        val position: TextView? = view.findViewById(R.id.rv_work_position)
        val duration: TextView? = view.findViewById(R.id.rv_work_duration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_work, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val work: Work = workList[position]
        val logo = viewHolder.logo
        val title = viewHolder.title
        val position = viewHolder.position
        val duration = viewHolder.duration

        logo?.setBackgroundResource(work.logo)
        title?.text = work.title
        position?.text = work.position
        duration?.text = work.duration
    }

    override fun getItemCount(): Int {
        return workList.size
    }
}
