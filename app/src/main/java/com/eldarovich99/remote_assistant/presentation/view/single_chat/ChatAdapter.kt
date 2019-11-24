package com.eldarovich99.remote_assistant.presentation.view.single_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.domain.models.Message


class SingleChatAdapter : RecyclerView.Adapter<SingleChatAdapter.SingleViewHolder>() {
    val items = generateSequence { Message(1) }.take(2).toList()
        .plus(generateSequence { Message(2) }.take(2).toList())
        .plus(generateSequence { Message(1) }.take(1).toList())
        .plus(generateSequence { Message(2) }.take(1).toList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
        val view : View
        view = when (viewType){
            Message.OWNER -> LayoutInflater.from(parent.context).inflate(R.layout.your_message, parent, false)
            Message.COMPANION -> LayoutInflater.from(parent.context).inflate(R.layout.companion_message, parent, false)
            else -> throw Exception("Unknown message type")
        }
        return SingleViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SingleViewHolder, position: Int) {
       // holder.itemNameTextView.text = "${holder.itemNameTextView.text.toString().substringBefore(' ')} ${holder.adapterPosition}"
    }

    class SingleViewHolder(view: View) : RecyclerView.ViewHolder(view){
        //val itemNameTextView = view.findViewById<TextView>(R.id.nameTextView)
    }
}