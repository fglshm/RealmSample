package com.fglshm.realmsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.*


class TodoActivity : AppCompatActivity(), TodoContract.View {

    private val logTag = TodoActivity::class.java.simpleName
    private lateinit var realm: Realm
    private lateinit var mAdapter: TodoAdapter

    private val presenter by lazy { TodoPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = TodoAdapter()
        recycler.adapter = mAdapter

        presenter.onCreate()

        button.setOnClickListener {
            presenter.onSaveButtonClick(edit.text.toString())
            edit.text = null
            edit.clearFocus()
        }
    }

    override fun showProgress() {
        progress.setVisible()
    }

    override fun hideProgress() {
        progress.setInvisible()
    }

    override fun addTodo(what: Todo) {
        mAdapter.add(what)
    }

    override fun addTodos(what: RealmResults<Todo>) {
        mAdapter.addAll(what)
    }

    private fun showLog(text: Any?) = Log.d(logTag, text?.toString() ?: "ERROR: text is null")

}
