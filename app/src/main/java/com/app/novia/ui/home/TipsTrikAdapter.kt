package com.app.novia.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.novia.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TipsTrikAdapter (val tipsTrikList: ArrayList<TipsTrikModel>) : RecyclerView.Adapter<TipsTrikAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_tips_trik, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tipsTrikList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (img, title, description) = tipsTrikList[position]

        Glide.with(holder.itemView.context)
            .load(img)
            .apply(RequestOptions())
            .into(holder.imgTipsTrik)
        holder.titleTipsTrik.text = title
        holder.descTipsTrik.text = description
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgTipsTrik: ImageView = itemView.findViewById(R.id.imgTipsTrik)
        var titleTipsTrik: TextView = itemView.findViewById(R.id.titleTipsTrik)
        var descTipsTrik: TextView = itemView.findViewById(R.id.descTipsTrik)
    }
}