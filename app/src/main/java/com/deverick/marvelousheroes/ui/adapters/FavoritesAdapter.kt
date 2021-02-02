package com.deverick.marvelousheroes.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deverick.marvelousheroes.databinding.ItemCharacterBinding
import com.deverick.marvelousheroes.models.Character

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private lateinit var binding: ItemCharacterBinding

    inner class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {

        binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FavoritesViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Character) -> Unit)? = null

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val char = differ.currentList[position]

        holder.itemView.apply {
            binding.charName.text = char.name
            Glide.with(this)
                .load("${char.thumbnail.path}/portrait_xlarge.${char.thumbnail.extension}")
                .into(binding.charAvatar)
            binding.charAvatar.contentDescription = char.name

            setOnClickListener {
                onItemClickListener?.let { it(char) }
            }
        }
    }

    fun setOnItemClickListener(listener: (Character) -> Unit) {
        onItemClickListener = listener
    }

}