package com.example.harrypottercaracters.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypottercaracters.R
import com.example.harrypottercaracters.models.other.RVViewModel
import com.squareup.picasso.Picasso
import java.io.IOException

class RVAdapter(private val mList: List<RVViewModel>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        holder.textViewActor.text = ItemsViewModel.actor
        holder.textViewCharacter.text = ItemsViewModel.character
       // holder.textViewHouse.text = ItemsViewModel.house
      val picasso = Picasso.get()
//        picasso.load(ItemsViewModel.imageUrl).into(holder.imageView)

        if (ItemsViewModel.imageUrl.isNullOrEmpty()) {
            holder.imageView.setImageResource(R.drawable.ic_launcher_background);
        } else {
            picasso.load(ItemsViewModel.imageUrl).into(holder.imageView)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textViewActor: TextView = itemView.findViewById(R.id.actorTV)
        val textViewCharacter: TextView = itemView.findViewById(R.id.characterTV)
        val textViewHouse: TextView = itemView.findViewById(R.id.houseTV)

    }
}