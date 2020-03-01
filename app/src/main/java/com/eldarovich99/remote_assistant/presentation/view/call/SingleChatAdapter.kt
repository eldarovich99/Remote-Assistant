package com.eldarovich99.remote_assistant.presentation.view.call

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.domain.models.Message
import javax.inject.Inject


class SingleChatAdapter @Inject constructor(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val items = generateSequence { Message(1) }.take(2).toList()
        .plus(generateSequence { Message(3) }.take(2).toList())
        .plus(generateSequence { Message(4) }.take(2).toList())
        .plus(generateSequence { Message(2) }.take(2).toList())
        .plus(generateSequence { Message(1) }.take(1).toList())
        .plus(generateSequence { Message(2) }.take(1).toList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            Message.OWNER ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.owner_message, parent, false)
                SingleViewHolder(view)
            }
            Message.COMPANION -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.companion_message, parent, false)
                SingleViewHolder(view)
            }
            Message.OWNER_FILE -> {
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.owner_message_pdf,
                        parent,
                        false
                    )
                view.setOnClickListener {
                    //presenter.downloadFile("https://www.innerfidelity.com/images/AudioTechnicaATHIM02.pdf")
                }
                FileViewHolder(view)
            }
            Message.COMPANION_FILE -> {
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.companion_message_pdf,
                        parent,
                        false
                    )
                view.setOnClickListener {
                    //presenter.downloadFile("https://www.innerfidelity.com/images/LogitechUE900.pdf")
                }
                FileViewHolder(view)
            }
            else -> throw Exception("Unknown message type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       // holder.itemNameTextView.text = "${holder.itemNameTextView.text.toString().substringBefore(' ')} ${holder.adapterPosition}"
    }

    class SingleViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val itemNameTextView: TextView = view.findViewById(R.id.messageTextView)
    }

    class FileViewHolder(view: View): RecyclerView.ViewHolder(view){
        val fileNameTextView: TextView = view.findViewById(R.id.fileTitleTextView)
        val fileSizeTextView: TextView = view.findViewById(R.id.fileSizeTextView)
    }
}