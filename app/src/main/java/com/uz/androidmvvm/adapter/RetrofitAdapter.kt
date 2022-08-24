package com.uz.androidmvvm.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uz.androidmvvm.activity.MainActivity
import com.uz.androidmvvm.R
import com.uz.androidmvvm.activity.CreateActivity
import com.uz.androidmvvm.activity.DetailActivity
import com.uz.androidmvvm.model.Note
import com.uz.androidmvvm.utils.Utils


/**
 * Created by Eldor Turgunov on 22.08.2022.
 * Android mvvm
 * eldorturgunov777@gmail.com
 */
class RetrofitAdapter(var context: Context, var list: ArrayList<Note>, var activity: MainActivity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_poster_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val note: Note = list[position]
        if (holder is ViewHolder) {
            holder.title.text = note.title
            holder.body.text = note.body
            holder.itemView.setOnClickListener { view: View? ->
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("IdExtra", note.id)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
//                (context as Activity).finish()
            }
            holder.longClick.setOnLongClickListener {
                dialogPoster(note)
                false
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var body: TextView
        var longClick: LinearLayout

        init {
            title = itemView.findViewById(R.id.title)
            body = itemView.findViewById(R.id.body)
            longClick = itemView.findViewById(R.id.longClick)
        }
    }

    private fun dialogPoster(note: Note) {
        val title = "Delete"
        val body = "Do you want to delete?"
        Utils.customDialog(activity, title, body, object : Utils.DialogListener {
            override fun onPositiveClick() {
                activity.mainPresenter.apiPostDelete(note)
            }

            override fun onNegativeClick() {
            }

        })
    }
}