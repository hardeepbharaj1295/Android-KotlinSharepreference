package com.hardeep.sqlitepractice.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hardeep.sharepreference.R
import com.hardeep.sqlitepractice.SqliteQueries
import com.hardeep.sqlitepractice.models.ListContent.ListItem

import kotlinx.android.synthetic.main.fragment_list.view.*
import org.w3c.dom.Text


class ListAdapter(
    private val mValues: ArrayList<ListItem>,
    private val context: Context
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private lateinit var sql: SqliteQueries

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.name
        holder.mContentView.text = item.email

        with(holder.mView) {
            tag = item
        }

        holder.upadte.setOnClickListener(View.OnClickListener {
            Log.e("Position",mValues.size.toString())
            mValues.removeAt(position)
            notifyDataSetChanged()
            
        })

        holder.delete.setOnClickListener(View.OnClickListener {
            sql = SqliteQueries(context)
            val del = sql.delete(item.id.toString())
            if (del>0){
                Toast.makeText(context,"Data Deleted",Toast.LENGTH_SHORT).show()
                mValues.removeAt(position)
            }
            else{
                Toast.makeText(context,"Please Try Again",Toast.LENGTH_SHORT).show()
            }
            notifyDataSetChanged()
        })
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content
        val upadte: TextView = mView.update
        val delete: TextView = mView.delete

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
