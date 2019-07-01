package com.hardeep.sqlitepractice.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hardeep.sharepreference.R
import com.hardeep.sqlitepractice.SqliteColumns
import com.hardeep.sqlitepractice.SqliteQueries
import com.hardeep.sqlitepractice.adapters.ListAdapter
import com.hardeep.sqlitepractice.models.ListContent

import com.hardeep.sqlitepractice.models.ListContent.ListItem

class ListFragmentData : Fragment() {

    private var columnCount = 1
   // private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_list, container, false)

        //recyclerView = view.findViewById(R.id.list)

        ListContent.ITEMS.clear()
        val sql = SqliteQueries(requireContext())
        val cursor = sql.fetchALl()
        if (cursor.moveToFirst()){
            do {
                Log.e("Response",cursor.getString(cursor.getColumnIndex(SqliteColumns.COLUMN_NAME)))
                val listItem = ListItem(cursor.getInt(cursor.getColumnIndex(SqliteColumns.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(SqliteColumns.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(SqliteColumns.COLUMN_EMAIL)),
                    cursor.getLong(cursor.getColumnIndex(SqliteColumns.COLUMN_NUMBER)),
                    cursor.getString(cursor.getColumnIndex(SqliteColumns.COUNTRY)),
                    cursor.getString(cursor.getColumnIndex(SqliteColumns.PASSWORD)),
                    cursor.getString(cursor.getColumnIndex(SqliteColumns.GENDER)))

                ListContent.ITEMS.add(listItem)
            }
            while (cursor.moveToNext());
        }
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = ListAdapter(ListContent.ITEMS,requireContext())
            }
        }
        return view
    }
}
