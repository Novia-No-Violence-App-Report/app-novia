package com.app.novia.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.novia.core.R
import com.app.novia.core.databinding.NewsRowBinding
import com.app.novia.core.domain.model.Article
import com.bumptech.glide.Glide
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NewsAdapter :
    RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {

    private var listData = ArrayList<Article>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Article>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.news_row, viewGroup, false)
        return ListViewHolder(view)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val article = listData[position]
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(article)
        }

        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date: Date = dateFormat.parse(article.date)
        val formatter: DateFormat = SimpleDateFormat("dd MMM yyyy")
        val dateStr: String = formatter.format(date)

        Glide.with(holder.itemView.context)
            .load(article.imageUrl)
            .centerCrop()
            .into(holder.binding.viewnewsImageView)

        holder.binding.viewnewsDateTextview.text = dateStr
        holder.binding.viewnewsJudulTextview.text = article.title

    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var binding = NewsRowBinding.bind(itemView)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Article)
    }

    override fun getItemCount(): Int = listData.size

}