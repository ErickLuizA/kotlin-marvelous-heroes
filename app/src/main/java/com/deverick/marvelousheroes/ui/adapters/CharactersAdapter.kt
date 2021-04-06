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


    private var onItemClickListener: ((Character) -> Unit)? = null

    fun setOnItemClickListener(listener: (Character) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CharacterViewHolder(binding.root)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

        val char = differ.currentList[position]

        holder.itemView.apply {
            binding.charName.text = char.name
            Glide.with(this)
                .load("${char.thumbnail.path}/standard_fantastic.${char.thumbnail.extension}")
                .into(binding.charAvatar)
            binding.charAvatar.contentDescription = char.name

            setOnClickListener {
                onItemClickListener?.let { it(char) }
            }
        }
    }
}