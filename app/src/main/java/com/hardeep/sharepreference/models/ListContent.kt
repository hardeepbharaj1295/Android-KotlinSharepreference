package com.hardeep.sqlitepractice.models

import java.util.ArrayList

object ListContent {

    val ITEMS: ArrayList<ListItem> = ArrayList()

    data class ListItem(val id: Int, val name: String, val email: String,val number: Long,val country: String,val password: String,val gender: String) {
        override fun toString(): String = name
    }
}
