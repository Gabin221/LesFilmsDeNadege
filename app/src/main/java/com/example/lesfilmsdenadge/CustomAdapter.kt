package com.example.lesfilmsdenadge

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomAdapter(private val mList: List<FilmsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        val steel = ItemsViewModel.steelbook

        if (steel.equals("true")) {
            holder.infos.setBackgroundColor(Color.parseColor("#FFD700"))
        }

        holder.textView.text = ItemsViewModel.titre
        holder.textRealisateur.text = ItemsViewModel.realisateur
        holder.textDateSortie.text = ItemsViewModel.sortie
        Glide.with(holder.imageView.context)
            .load(ItemsViewModel.image)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val textRealisateur: TextView = itemView.findViewById(R.id.textRealisateur)
        val textDateSortie: TextView = itemView.findViewById(R.id.textDateSortie)
        val infos: LinearLayout = itemView.findViewById(R.id.infos)
    }
}
