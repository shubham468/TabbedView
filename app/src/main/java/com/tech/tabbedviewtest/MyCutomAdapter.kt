package com.tech.tabbedviewtest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyCutomAdapter(val context: Context, var arrylist: ArrayList<NewsDetails>) :
    RecyclerView.Adapter<MyCutomAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)
        val remaining = itemView.findViewById<TextView>(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cutomview, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (arrylist.size != 0) {
            holder.title.text = arrylist[position].title.uppercase()
            holder.remaining.text =
                "${arrylist[position].category}\n".uppercase() +
                        "${arrylist[position].source}\n" +
                        "${arrylist[position].description}"
        }
    }

    override fun getItemCount(): Int {
        return arrylist.size
    }

    fun setModel(arrayList3: ArrayList<NewsDetails>) {
        this.arrylist = arrayList3
        notifyDataSetChanged()

    }
}