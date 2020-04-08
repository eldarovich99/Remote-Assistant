package com.eldarovich99.remote_assistant.presentation.view.chats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eldarovich99.remote_assistant.R
import com.eldarovich99.remote_assistant.domain.models.ContactBrief
import com.eldarovich99.remote_assistant.routing.CallScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ChatsAdapter @Inject constructor(var router: Router) : RecyclerView.Adapter<ChatsAdapter.ChatsViewHolder>() {
    var bindCounter = 0
    val items = mutableListOf<ContactBrief>()

    fun updateData(data: List<ContactBrief>){
        CoroutineScope(Dispatchers.IO).launch{
            val result = DiffUtil.calculateDiff(object: DiffUtil.Callback(){
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return items[oldItemPosition].id == data[newItemPosition].id
                }

                override fun getOldListSize(): Int {
                    return items.size
                }

                override fun getNewListSize(): Int {
                    return data.size
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return items[oldItemPosition] == data[newItemPosition]
                }

            })
            withContext(Dispatchers.Main){
                items.clear()
                items.addAll(data)
                result.dispatchUpdatesTo(this@ChatsAdapter)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.people_item_chats, parent, false)
        val holder = ChatsViewHolder(view)
        /*holder.itemView.setOnClickListener {
            router.navigateTo(CallScreen())
        }*/
        holder.invisibleButton.setOnClickListener {
            router.navigateTo(CallScreen())
        }
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
        holder.itemNameTextView.text = items[holder.adapterPosition].name //"${holder.itemNameTextView.text.toString().substringBefore(' ')} ${holder.adapterPosition}"
        holder.itemView.isSelected = items[holder.adapterPosition].isEnabled == true
    }

    class ChatsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val itemNameTextView : TextView = view.findViewById(R.id.nameTextView)
        val invisibleButton : Button = view.findViewById(R.id.invisibleButton)
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