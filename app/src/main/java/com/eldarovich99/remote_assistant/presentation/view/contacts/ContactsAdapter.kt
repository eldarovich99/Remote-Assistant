package com.eldarovich99.remote_assistant.presentation.view.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.domain.models.User
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ContactsAdapter @Inject constructor(var router: Router) : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {
    val items = generateSequence { User() }.take(50).toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.people_item_contacts, parent, false)
        view.setOnClickListener {
            //router.navigateTo(SingleChatScreen())
        }
        return ContactsViewHolder(view)
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