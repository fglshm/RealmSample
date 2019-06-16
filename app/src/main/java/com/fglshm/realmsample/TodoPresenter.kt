package com.fglshm.realmsample

import io.realm.RealmResults

class TodoPresenter(
    private val view: TodoContract.View
) : TodoContract.Presenter, TodoContract.InteractorOutput {

    private val interactor by lazy { TodoInteractor(this) }

    override fun onCreate() {
        view.showProgress()
        interactor.fetchTodos()
    }

    override fun onFetch(what: RealmResults<Todo>) {
        view.hideProgress()
        view.addTodos(what)
    }

    override fun onSaveButtonClick(task: String) {
        interactor.saveTodo(task)
    }

    override fun onSave(what: Todo) {
        view.addTodo(what)
    }
}