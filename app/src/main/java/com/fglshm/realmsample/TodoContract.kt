package com.fglshm.realmsample

import io.realm.RealmResults

interface TodoContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun addTodo(what: Todo)
        fun addTodos(what: RealmResults<Todo>)
    }

    interface Presenter {
        fun onCreate()
        fun onSaveButtonClick(task: String)
    }

    interface Interactor {
        fun fetchTodos()
        fun saveTodo(task: String)
    }

    interface InteractorOutput {
        fun onFetch(what: RealmResults<Todo>)
        fun onSave(what: Todo)
    }

}