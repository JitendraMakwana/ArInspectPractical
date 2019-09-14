package com.app.arinspect.arinspectpracticaldemo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.app.arinspect.arinspectpracticaldemo.R
import com.app.arinspect.arinspectpracticaldemo.dataModel.Rows

/**
 * @author Jitendra Makwana
 * @created on 15-Sept-19.
 */
class RecyclerViewAdapter(var rowsList: ArrayList<Rows>?) :
        RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rowsList!!.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindData(rowsList, position)
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        var txtDescription: TextView = itemView.findViewById(R.id.txtDescription)
        fun bindData(rowList: ArrayList<Rows>?, position: Int) {
            txtTitle.text = rowList!![position].title
            txtDescription.text = rowList[position].description

        }
    }
}