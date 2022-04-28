package ru.ruslan.testpagin.screens.utils

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.ruslan.testpagin.R
import ru.ruslan.testpagin.network.api.model.CharacterData
import ru.ruslan.testpagin.screens.DetailCharacterActivity
import ru.ruslan.testpagin.utils.APP_ACTIVITY

class RecyclerViewAdapter(var context: Context) : PagingDataAdapter<CharacterData, RecyclerViewAdapter.MyViewHolder>(
    DiffUtilCallBack()
) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        getItem(position)?.let { holder.bind(it) }

        holder.itemView.setOnClickListener {

            var character = getItem(position)
            val intent = Intent(APP_ACTIVITY, DetailCharacterActivity::class.java)
            intent.putExtra("CHARACTER", character)
            context.startActivity(intent)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)

        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageView: ImageView = view.findViewById(R.id.imageView)
        private val tvName: TextView = view.findViewById(R.id.tv_name)
        private val tvSpecies: TextView = view.findViewById(R.id.tv_species)
        private val tvGender: TextView = view.findViewById(R.id.tv_gender)

        fun bind(data: CharacterData) {
            tvName.text = data.name
            tvSpecies.text = data.species
            tvGender.text = data.gender

            Picasso.get().load(data.image).into(imageView)


        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<CharacterData>() {
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.species == newItem.species
        }

    }

}