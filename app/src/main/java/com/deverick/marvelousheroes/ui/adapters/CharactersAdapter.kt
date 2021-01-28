package com.deverick.marvelousheroes.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deverick.marvelousheroes.databinding.ItemCharacterBinding
import com.deverick.marvelousheroes.models.Character

class CharactersAdapter() :
    PagingDataAdapter<Character, CharactersAdapter.CharacterViewHolder>(
        CharactersDiffCallback()
    ) {

    private lateinit var binding: ItemCharacterBinding

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CharacterViewHolder(binding.root)
    }

    private var onItemClickListener: ((Character) -> Unit)? = null


    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

        getItem(position)?.let { char ->
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
    }

    fun setOnItemClickListener(listener: (Character) -> Unit) {
        onItemClickListener = listener
    }
}

private class CharactersDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}