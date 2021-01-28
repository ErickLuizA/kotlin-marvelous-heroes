package com.deverick.marvelousheroes.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deverick.marvelousheroes.databinding.FragmentCharactersBinding

class CharactersLoadStateAdapter :
    LoadStateAdapter<CharactersLoadStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(private val binding: FragmentCharactersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.paginationProgressBar.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding =
            FragmentCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return LoadStateViewHolder(binding)
    }
}