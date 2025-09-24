package com.vapp.admobexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vapp.admobexample.R
import com.vapp.admoblibrary.utils.Utils
import java.util.*

class MainAdapter(private val list: ArrayList<ItemModel>, private val listener: ClickListener) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        return ViewHolder(inflater.inflate(R.layout.item_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.textViewTitle.text = list[position].title
        holder.textViewTitle.setOnClickListener(View.OnClickListener {
            Utils.getInstance().showMessenger(context,position.toString());
        })
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView
        init {
            textViewTitle = itemView.findViewById(R.id.text_reverse)

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}