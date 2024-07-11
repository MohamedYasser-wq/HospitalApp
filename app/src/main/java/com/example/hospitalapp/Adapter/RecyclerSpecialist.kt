package com.example.hospitalapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalapp.R

class RecyclerSpecialist(private val listener: OnClick) : RecyclerView.Adapter<RecyclerSpecialist.Holder>() {

    private var list = ArrayList<String>()


    fun setList(list2: ArrayList<String>) {
        list = list2

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.specialist_card, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val specialist: TextView = itemView.findViewById(R.id.Tv_Specialist)

        fun bind(position: Int) {
            specialist.text = list[position]

            itemView.setOnClickListener {
                listener.getType(list[position])

            }
        }
    }

    interface OnClick {
        fun getType(type: String)
    }
}
