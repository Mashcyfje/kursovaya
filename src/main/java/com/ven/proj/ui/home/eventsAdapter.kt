package com.ven.proj.ui.home

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ven.proj.R

class eventsAdapter(val context: Context , val eventsArray:ArrayList<String>, val timeArrayList: ArrayList<String> , clicked:ItemClick) : RecyclerView.Adapter<eventsAdapter.ViewHolder>() {

   var clicked = clicked
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.event_item , p0 , false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return  eventsArray.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.eventText.text = eventsArray[p1]
        p0.eventText.textSize = 18f
        p0.eventText.setTextColor(Color.BLACK)
        p0.timeText.text = timeArrayList[p1]
        p0.timeText.textSize = 18f
        p0.timeText.setTextColor(Color.BLACK)
        p0.delBtn.setOnClickListener {
            clicked.getItemClick(p1)
        }
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val eventText = itemView.findViewById<TextView>(R.id.textTitle)
        val timeText = itemView.findViewById<TextView>(R.id.textTime)
        val delBtn = itemView.findViewById<ImageView>(R.id.delBtn)
    }


}