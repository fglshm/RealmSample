package com.fglshm.realmsample

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class Todo(
    @PrimaryKey
    open var id: String = UUID.randomUUID().toString(),
    @Required
    open var task: String = ""
) : RealmObject()
