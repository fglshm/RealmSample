package com.fglshm.realmsample

import android.util.Log
import io.realm.Realm
import java.util.*

class TodoInteractor(
    private val output: TodoContract.InteractorOutput
) : TodoContract.Interactor {

    private val logTag = TodoInteractor::class.java.simpleName
    private var realm: Realm? = null

    init {
        App.mContext?.let {
            Realm.init(it)
            realm = Realm.getDefaultInstance()
        } ?: Log.d(logTag, "can't get context.")
    }

    override fun fetchTodos() {
        val todos = realm?.where(Todo::class.java)?.findAll()
        todos?.let {
            output.onFetch(todos)
        }
    }

    override fun saveTodo(task: String) {
        realm?.executeTransaction {
            val todo = realm?.createObject(Todo::class.java, UUID.randomUUID().toString())
            todo?.task = task
            todo?.let {
                realm?.copyToRealm(todo)
                output.onSave(todo)
            }
        }
    }
}