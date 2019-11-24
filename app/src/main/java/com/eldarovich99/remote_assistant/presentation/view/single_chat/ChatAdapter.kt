package com.eldarovich99.remote_assistant.presentation.view.single_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.domain.models.Message
import com.eldarovich99.remote_assistant.domain.models.User

class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {
    val items = generateSequence { User() }.take(50).toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view : View
        when (viewType){
            Message.OWNER -> LayoutInflater.from(parent.context).inflate(R.layout.your_message, parent, false)
            Message.COMPANION -> LayoutInflater.from(parent.context).inflate(R.layout.companion_message, parent, false)
        }
        return ContactsViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.itemNameTextView.text = "${holder.itemNameTextView.text.toString().substringBefore(' ')} ${holder.adapterPosition}"
        holder.itemView.isSelected = items[holder.adapterPosition].isEnabled
    }

    class ContactsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val itemNameTextView = view.findViewById<TextView>(R.id.nameTextView)
    }
}