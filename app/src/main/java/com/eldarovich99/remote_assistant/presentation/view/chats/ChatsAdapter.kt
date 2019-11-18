package com.eldarovich99.remote_assistant.presentation.view.chats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.domain.models.User

class ChatsAdapter() : RecyclerView.Adapter<ChatsAdapter.ChatsViewHolder>() {
    val items = generateSequence { User() }.take(50).toList()
    var selectedView : View?=null
    var selectedItemPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.people_item, parent, false)
        val holder = ChatsViewHolder(view)
        holder.itemView.setOnClickListener {
            selectedView?.isSelected = false
            it.isSelected = true
            selectedView = it
            selectedItemPosition = holder.adapterPosition
        }
           // View.inflate(parent.context, R.layout.people_item, null)
        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        holder.itemView.isSelected = holder.adapterPosition == selectedItemPosition
        //holder.offerTitle.text = itemMocks[holder.adapterPosition].offerTitle
       // holder.organizationName.text = itemMocks[holder.adapterPosition].organizationName
       // holder.offerImageView.setImageResource(R.drawable.mock_offer) // replace when backend will be ready
    }

    class ChatsViewHolder(view: View) : RecyclerView.ViewHolder(view){
    }
}