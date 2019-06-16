package com.fglshm.realmsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.todo_item.view.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val logTag = MainActivity::class.java.simpleName
    private lateinit var realm: Realm
    private lateinit var mAdapter: TodosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = TodosAdapter()
        recycler.adapter = mAdapter

        Realm.init(this)
        realm = Realm.getDefaultInstance()

        fetchTodos()

        button.setOnClickListener {
            realm.executeTransaction {
                val todo = realm.createObject(Todo::class.java, UUID.randomUUID().toString())
                todo.task = edit.text.toString()
                realm.copyToRealm(todo)
                showLog("[ SAVED ]")
                fetchTodos()
            }
        }
    }

    private fun fetchTodos() {
        val todos = realm.where(Todo::class.java).findAll()
        mAdapter.add(todos)
    }

    private fun deleteAllTodos() {
        val todos = realm.where(Todo::class.java).findAll()
        realm.executeTransaction {
            todos.deleteAllFromRealm()
            showLog("[ ALL DELETED ]")
        }
    }

    private fun showLog(text: Any?) = Log.d(logTag, text?.toString() ?: "ERROR: text is null")

    private class TodosAdapter : RecyclerView.Adapter<ItemViewHolder>() {

        private val todos = mutableListOf<Todo>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
            return ItemViewHolder(view)
        }

        override fun getItemCount(): Int = todos.size
        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.itemView.text_todo.text = todos[position].task
        }

        fun add(what: RealmResults<Todo>) {
            todos.addAll(what)
            notifyDataSetChanged()
        }

    }

    private class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
