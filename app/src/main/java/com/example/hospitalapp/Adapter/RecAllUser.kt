
package com.example.hospitalapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalapp.Data.AllUser
import com.example.hospitalapp.R

class RecAllUser(private val listener: OnClick):RecyclerView.Adapter<RecAllUser.Holder>() {

    lateinit var list:AllUser

    fun setListtt(list2Users: AllUser) {
        list = list2Users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.employee_card, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return list.data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
          holder.binding(position)
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name:TextView = itemView.findViewById(R.id.tv_Name)
        val specialist:TextView = itemView.findViewById(R.id.tv_Specialist)

        fun binding(position: Int){

            name.text=list.data.get(position).first_name
            specialist.text=list.data.get(position).type
      itemView.setOnClickListener {

             listener.getId(list.data.get(position).id)
              }

        }

    }
    interface OnClick {
        fun getId(id: Int)
    }


}