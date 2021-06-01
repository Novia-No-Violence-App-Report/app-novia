package com.app.novia.ui.welcomeactivities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.app.novia.R
import com.bumptech.glide.Glide

class ViewPagerAdapter(private var title: List<String>, private var images: List<Int>) :
    RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.imgWelcome)

        init {
            itemImage.setOnClickListener { v: View ->
                val position = adapterPosition
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.slider_welcome, parent, false))
    }

    override fun getItemCount(): Int {
        return title.size
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {
        var image = arrayOf(
            R.drawable.welcomeillustration_1,
            R.drawable.welcomeillustration_2,
            R.drawable.welcomeillustration_3)
        holder.itemImage.setImageResource(image[position])
    }

}