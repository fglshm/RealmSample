package com.fglshm.realmsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmResults
import kotlinx.android.synthetic.main.todo_item.view.*

class TodoAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    private val todos = mutableListOf<Todo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = todos.size
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemView.text_todo.text = todos[position].task
    }

    fun addAll(what: RealmResults<Todo>) {
        todos.addAll(what)
        notifyDataSetChanged()
    }

    fun add(what: Todo) {
        todos.add(what)
        notifyItemInserted(itemCount)
    }

}
