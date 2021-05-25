package com.app.novia.core.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.novia.core.R
import com.app.novia.core.domain.model.ChatEntity
import java.text.DateFormat


class CustomAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list = ArrayList<ChatEntity>()
    fun addData(chatMessage: ChatEntity?) {
        if (chatMessage == null) return
        list.add(chatMessage)
        notifyItemInserted(list.size)
    }

    private inner class MessageInViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var messageTV: TextView = itemView.findViewById(R.id.tv_incoming_message)
        var timeTv: TextView = itemView.findViewById(R.id.tv_incoming_time)
        fun bind(position: Int) {
            val messageModel: ChatEntity = list[position]
            messageTV.text = messageModel.message
            timeTv.text = messageModel.timeStamp
        }

    }

    private inner class MessageOutViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var messageTv: TextView = itemView.findViewById(R.id.tv_outgoing_message)
        var timeTv: TextView = itemView.findViewById(R.id.tv_outgoing_time)
        fun bind(position: Int) {
            val messageModel: ChatEntity = list[position]
            messageTv.text = messageModel.message
            timeTv.text = messageModel.timeStamp
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == MESSAGE_TYPE_IN) {
            MessageInViewHolder(
                LayoutInflater.from(context).inflate(R.layout.chat_incoming_row, parent, false)
            )
        } else MessageOutViewHolder(
            LayoutInflater.from(context).inflate(R.layout.chat_outgoing_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].senderIsBot == true) {
            (holder as MessageInViewHolder).bind(position)
        } else {
            (holder as MessageOutViewHolder).bind(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].senderIsBot == true) {
            MESSAGE_TYPE_IN
        } else {
            MESSAGE_TYPE_OUT
        }
    }

    companion object {
        const val MESSAGE_TYPE_IN = 1
        const val MESSAGE_TYPE_OUT = 2
    }

}