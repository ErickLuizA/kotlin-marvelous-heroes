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

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    private lateinit var binding: ItemCharacterBinding

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CharacterViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = differ.currentList[position]

        holder.itemView.apply {
            binding.charName.text = character.name
            Glide.with(this)
                .load("${character.thumbnail.path}/portrait_xlarge.${character.thumbnail.extension}")
                .into(binding.charAvatar)
            binding.charAvatar.contentDescription = character.name
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}