package com.app.novia.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.novia.core.R
import com.app.novia.core.databinding.ChatIncomingRowBinding

class IncomingChatAdapter: RecyclerView.Adapter<IncomingChatAdapter.ListViewHolder>() {

    private var listData = ArrayList<String>()

    fun addData(chatMessage: String?) {
        if (chatMessage == null) return
        listData.add(chatMessage)
        notifyItemInserted(listData.size)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var incomingBinding = ChatIncomingRowBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_incoming_row, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val message = listData[position]
        holder.incomingBinding.tvIncomingMessage.text = message
    }

    override fun getItemCount(): Int = listData.size
}