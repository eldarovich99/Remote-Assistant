package com.eldarovich99.remote_assistant.presentation.view.chats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.domain.models.User

class ChatsAdapter(val callback : OnItemClicked) : RecyclerView.Adapter<ChatsAdapter.ChatsViewHolder>() {
    companion object{
        interface OnItemClicked{
            fun onClick(position: Int)
        }
    }
    val items = generateSequence { User() }.take(50).toList()
   // var selectedView : View?=null
   // var selectedItemPosition = -1
    var bindCounter = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.people_item_chats, parent, false)
        val holder = ChatsViewHolder(view)
        callback.onClick(holder.adapterPosition)
        // holder.itemView.setOnClickListener {
      //     selectedView?.isSelected = false
      //     it.isSelected = true
      //     selectedView = it
      //     selectedItemPosition = holder.adapterPosition
      // }
           // View.inflate(parent.context, R.layout.people_item_chats, null)
        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
       // holder.itemView.isSelected = holder.adapterPosition == selectedItemPosition
        holder.itemNameTextView.text = "${holder.itemNameTextView.text.toString().substringBefore(' ')} ${holder.adapterPosition}"
        holder.itemView.isSelected = items[holder.adapterPosition].isEnabled
        //holder.offerTitle.text = itemMocks[holder.adapterPosition].offerTitle
       // holder.organizationName.text = itemMocks[holder.adapterPosition].organizationName
       // holder.offerImageView.setImageResource(R.drawable.mock_offer) // replace when backend will be ready
    }

    class ChatsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val itemNameTextView = view.findViewById<TextView>(R.id.nameTextView)
    }

    fun disable(adapterPosition: Int){
        items[adapterPosition].isEnabled = false
        notifyItemChanged(adapterPosition)
    }

    fun enable(adapterPosition : Int){
        //selectedItemPosition = adapterPosition
        items[adapterPosition].isEnabled = true
        notifyItemChanged(adapterPosition)
    }
}